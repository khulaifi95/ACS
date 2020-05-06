// Name: Zhangda Xu
// Student ID: 2088192
//
// Assignment goals achieved:
//   - block scan
//   - full scan for large vectors
//   - bank conflict avoidance optimization
//
// Time to execute the different scans on a vector of 10,000,000 entries:
//   - Block scan without BCAO: *TIME-1*
//   - Block scan with BCAO: *TIME-2*
//   - Full scan without BCAO: *TIME-3*
//   - Full scan with BCAO: *TIME-4*
//
// CPU model:
// GPU model:
//
// Description
// A short description of any implementation details or performance improvement strategies that you successfully
// implemented and which improve upon a base level implementation of the target goals.

__global__ void prescan(float *g_odata, float *g_idata, int n)
{
    extern __shared__ float temp[]; // allocated on invocation

    int thid = threadIndx.x;
    int offset = 1;

    temp[2*thid] = g_idata[2*thid];
    temp[2*thid+1] = g_idata[2*thid+1];

    for (int d = n>>1; d > 0; d >>= 1)  // build sum in place up the tree
    {
        __syncthreads();

        if (thid < d)
        {
            int ai = offset*(2*thid+1)-1;
            int bi = offset*(2*thid+2)-1;

            temp[bi] += temp[ai];
        }
        offset *= 2;

    }

    if (thid==0) { temp[n -1] = 0;  }   // clear the last element

    for (int d =1; d < n; d *= 2)   // traverse down tree & build scan
    {
        offset >>= 1;
        __syncthreads();

        if (thid < d)
        {
            int ai = offset*(2*thid+1)-1;
            int bi = offset*(2*thid+2)-1;

            float t = temp[ai];
            temp[ai] = temp[bi];
            temp[bi] += t;
        }
    }

    __syncthreads();

    g_odata[2*thid] = temp[2*thid]; // write results to device memory
    g_odata[2*thid+1] = temp[2*thid+1];
}