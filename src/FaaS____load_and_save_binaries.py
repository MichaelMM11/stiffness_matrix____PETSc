#!/usr/bin/env python3
# -*- coding:utf-8 -*-

"""
purpose
    - to load binary data (vector, matrix)
    - to save binary data (vector, matrix)

workflow
    1) load .dat binary
    2) save .bat binary

requirement
    - file, filename of data in binary format (createBinary method)

remark
    - data (matrix, vector) is assumed to be available in binary format
    - no POSIX path is accepted, therefore the str() transformation
    - code closely related to FaaS____reconstruct_results_from_binaries.py

example

source
    - https://bitbucket.org/petsc/petsc4py/src/maint/demo/binary-io/matvecio.py
    - https://lists.mcs.anl.gov/mailman/htdig/petsc-users/2016-June/029691.html
"""

from pathlib import Path
from petsc4py import PETSc


# to set directory dependencies
current__dir = Path.cwd()
main__dir = current__dir.parents[0]
data__dir = main__dir.joinpath('data/test')


# to select data to load
matrix_filename = 'load_binary_matrix'
vector_filename = 'load_binary_vector'
suffix = '.dat'
matrix_file = Path(data__dir, matrix_filename).with_suffix(suffix)
vector_file = Path(data__dir, vector_filename).with_suffix(suffix)


# to load data from binaries
## str because POSIX is not accepted
viewer = PETSc.Viewer().createBinary(str(matrix_file), 'r')
A = PETSc.Mat().load(viewer)
viewer = PETSc.Viewer().createBinary(str(vector_file), 'r')
b = PETSc.Vec().load(viewer)


# to select vector,matrix to save
matrix_filename = 'save_binary_matrix'
vector_filename = 'save_binary_vector'
suffix = '.dat'
matrix_file = Path(data__dir, matrix_filename).with_suffix(suffix)
vector_file = Path(data__dir, vector_filename).with_suffix(suffix)

# to save data as binaries
viewer = PETSc.Viewer().createBinary(str(matrix_file), 'w')
viewer(A)
viewer = PETSc.Viewer().createBinary(str(vector_file), 'w')
viewer(b)
