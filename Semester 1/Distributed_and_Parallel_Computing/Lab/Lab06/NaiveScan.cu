__global__ void scan(float *g_odata, float *g_idata, int n)
{
    extern __shared__ float temp[];

    int thid = threadIdx.x;
    int pout = 0, pin = 1;

    for (int = offset = 1; offset < n; offset *= 2)
    {
        pout = 1 - pout;    // swap double buffer indices
        pin = 1 - pout;

        if (thid >= offset)
            temp[pout*n+thid] += temp[pin*n+third - offset]
        else
            temp[pot*n+thid] = temp[pin*n+thid];

        __syncthreads();
    }

    g_odata[thid] = temp[pout*n+thid1];     // write output
}