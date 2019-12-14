/**
 * Copyright 1993-2013 NVIDIA Corporation.  All rights reserved.
 *
 * Please refer to the NVIDIA end user license agreement (EULA) associated
 * with this source code for terms and conditions that govern your use of
 * this software. Any use, reproduction, disclosure, or distribution of
 * this software and related documentation outside the terms of the EULA
 * is strictly prohibited.
 *
 */

/**
 * Vector addition: C = A + B.
 *
 * This sample is a very basic sample that implements element by element
 * vector addition. It is the same as the sample illustrating Chapter 2
 * of the programming guide with some additions like error checking.
 *
 * Slightly modified to provide timing support
 */

#include <stdio.h>

// For the CUDA runtime routines (prefixed with "cuda_")
#include <cuda_runtime.h>

#include <helper_cuda.h>
#include <helper_functions.h>

// A helper macro to simplify handling cuda error checking
#define CUDA_ERROR( err, msg ) { \
if (err != cudaSuccess) {\
    printf( "%s: %s in %s at line %d\n", msg, cudaGetErrorString( err ), __FILE__, __LINE__);\
    exit( EXIT_FAILURE );\
}\
}

/**
 * Host version of vectorAdd
 *
 * Computes the vector addition of A and B into C. The 3 vectors have the same
 * number of elements numElements.
 */
__host__ void
vectorAdd_HOST(const float *A, const float *B, float *C, int numElements)
{
    int i ;

    for ( i = 0; i < numElements ; i ++)
    	C [i] = A [i] + B [i ];

}

/**
 * SINGLE thread code that works on Device
 *
 * Computes the vector addition of A and B into C. The 3 vectors have the same
 * number of elements numElements.
 */
__global__ void
vectorAdd_SINGLE_THREAD(const float *A, const float *B, float *C, int numElements)
{
    int i ;

    for ( i = 0; i < numElements ; i ++)
    	C [i] = A [i] + B [i ];

}

/**
 * CUDA Kernel Device code
 *
 * Computes the vector addition of A and B into C. The 3 vectors have the same
 * number of elements numElements.
 */
__global__ void
vectorAdd(const float *A, const float *B, float *C, int numElements)
{
    int i = blockDim.x * blockIdx.x + threadIdx.x;

    if (i < numElements)
    {
        C[i] = A[i] + B[i];
    }
}


static void compare_results(const float *vector1, const float *vector2, int numElements)
{
	for (int i = 0; i < numElements; ++i)
	{
		if (fabs(vector1[i] - vector2[i]) > 1e-5f)
		{
			fprintf(stderr, "Result verification failed at element %d!\n", i);
			exit (EXIT_FAILURE);
		}
	}
}



/**
 * Host main routine
 */
int
main(void)
{
    // Error code to check return values for CUDA calls
    cudaError_t err = cudaSuccess;

    // Create Host stopwatch timer
    StopWatchInterface * timer = NULL ;
    sdkCreateTimer (& timer );
    double h_msecs ;

    // Create Device timer event objects
    cudaEvent_t start , stop ;
    float d_msecs ;
    cudaEventCreate (& start );
    cudaEventCreate (& stop ) ;



    // Print the vector length to be used, and compute its size
    int numElements = 50000;
    size_t size = numElements * sizeof(float);
    printf("[Vector addition of %d elements]\n", numElements);

    // Allocate the host input vector A B and C
    float *h_A = (float *)malloc(size);
    float *h_B = (float *)malloc(size);
    float *h_C = (float *)malloc(size);

    // Allocate the host output vector that will contain the sum calculate by the Host
    float *h_SUM = (float *)malloc(size);

    // Verify that allocations succeeded
    if (h_A == NULL || h_B == NULL || h_C == NULL || h_SUM == NULL)
    {
        fprintf(stderr, "Failed to allocate host vectors!\n");
        exit(EXIT_FAILURE);
    }

    // Initialise the host input vectors
    for (int i = 0; i < numElements; ++i)
    {
        h_A[i] = rand()/(float)RAND_MAX;
        h_B[i] = rand()/(float)RAND_MAX;
    }

    //
    // Execute the vector addition on the Host and time it:
    //
    sdkStartTimer (& timer );
    vectorAdd_HOST(h_A, h_B, h_SUM, numElements);
    sdkStopTimer (& timer );
    h_msecs = sdkGetTimerValue (& timer );
    printf("Executed vector add of %d elements on the Host in = %.5fmSecs\n", numElements, h_msecs);



    // Allocate the device input vector A, B and C
    float *d_A = NULL;

    err = cudaMalloc((void **)&d_A, size);
    CUDA_ERROR(err, "Failed to allocate device vector A");

//    If you prefer, you can combine the above two lines into a single one as follows:
//    CUDA_ERROR(cudaMalloc((void **)&d_A, size), "Failed to allocate device vector A");

    float *d_B = NULL;
    err = cudaMalloc((void **)&d_B, size);
    CUDA_ERROR(err, "Failed to allocate device vector B");

    float *d_C = NULL;
    err = cudaMalloc((void **)&d_C, size);
    CUDA_ERROR(err, "Failed to allocate device vector C");

    // Copy the host input vectors A and B in host memory to the device input vectors in device memory
    printf("Copy input data from the host memory to the CUDA device\n");
    err = cudaMemcpy(d_A, h_A, size, cudaMemcpyHostToDevice);
    CUDA_ERROR(err, "Failed to copy vector A from host to device");

    err = cudaMemcpy(d_B, h_B, size, cudaMemcpyHostToDevice);
    CUDA_ERROR(err, "Failed to copy vector B from host to device");

    //
    // Execute the vector addition on the Device IN A SINGLE THREAD and time it:
    //

    cudaEventRecord( start, 0 );
    vectorAdd_SINGLE_THREAD<<<1, 1>>>(d_A, d_B, d_C, numElements);
    cudaEventRecord( stop, 0 );
    cudaEventSynchronize( stop );

    // wait for device to finish
    cudaDeviceSynchronize();

    err = cudaGetLastError();
    CUDA_ERROR(err, "Failed to launch vectorAdd kernel");

    err = cudaEventElapsedTime( &d_msecs, start, stop );
    CUDA_ERROR(err, "Failed to get elapsed time");

    printf("Executed vector add of %d elements on the Device in a SINGLE THREAD in = %.5fmSecs\n", numElements, d_msecs);



    // Copy the device result vector in device memory to the host result vector
    // in host memory.
    printf("Copy output data from the CUDA device to the host memory\n");
    err = cudaMemcpy(h_C, d_C, size, cudaMemcpyDeviceToHost);
    CUDA_ERROR(err, "Failed to copy vector C from device to host");

    // Verify that the result vector is correct
    compare_results(h_SUM, h_C, numElements);
    printf("Test PASSED\n");


    // Launch the Vector Add CUDA Kernel
    int threadsPerBlock = 256;

    // Note this pattern, based on integer division, for rounding up
    int blocksPerGrid = 1 + ((numElements - 1) / threadsPerBlock);

    printf("Launching the CUDA kernel with %d blocks of %d threads\n", blocksPerGrid, threadsPerBlock);

    //
    // Execute the vector addition on the Device in multiple threads and time it:
    //

    cudaEventRecord( start, 0 );
    vectorAdd<<<blocksPerGrid, threadsPerBlock>>>(d_A, d_B, d_C, numElements);
    cudaEventRecord( stop, 0 );
    cudaEventSynchronize( stop );

    // wait for device to finish
    cudaDeviceSynchronize();

    err = cudaGetLastError();
    CUDA_ERROR(err, "Failed to launch vectorAdd kernel");

    err = cudaEventElapsedTime( &d_msecs, start, stop );
    CUDA_ERROR(err, "Failed to get elapsed time");

    printf("Executed vector add of %d elements on the Device in %d blocks of %d threads in = %.5fmSecs\n",
    		numElements, blocksPerGrid, threadsPerBlock, d_msecs);



    // Copy the device result vector in device memory to the host result vector
    // in host memory.
    printf("Copy output data from the CUDA device to the host memory\n");
    err = cudaMemcpy(h_C, d_C, size, cudaMemcpyDeviceToHost);
    CUDA_ERROR(err, "Failed to copy vector C from device to host");

    // Verify that the result vector is correct
    compare_results(h_SUM, h_C, numElements);
    printf("Test PASSED\n");




    // Free device global memory
    err = cudaFree(d_A);
    CUDA_ERROR(err, "Failed to free device vector A");
    err = cudaFree(d_B);
    CUDA_ERROR(err, "Failed to free device vector B");
    err = cudaFree(d_C);
    CUDA_ERROR(err, "Failed to free device vector C");

    // Free host memory
    free(h_A);
    free(h_B);
    free(h_C);

    // Clean up the Host timer
    sdkDeleteTimer (& timer );

    // Clean up the Device timer event objects
    cudaEventDestroy ( start );
    cudaEventDestroy ( stop );

    // Reset the device and exit
    err = cudaDeviceReset();
    CUDA_ERROR(err, "Failed to reset the device");

    printf("Done\n");
    return 0;
}

