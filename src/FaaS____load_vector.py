#!/usr/bin/env python3
# -*- coding:utf-8 -*-

"""
purpose
    - to load a vector as PETSc object

worklfow
    1) execute the same code as 'load_matrix.py'
    2) take desired column (in most cases the 0th, because
    that's the way the values are stored)

requirement
    - matrix A must be given as matrix object
"""

from pathlib import Path
from scipy.io import mmread
from petsc4py import PETSc

import scipy


# to set directory dependencies
current__dir = Path.cwd()
main__dir = current__dir.parents[0]
data__dir = main__dir.joinpath('data/test')


# to select stiffness matrix, load vector
matrix_filename = '4x4_matrix____test'
suffix = '.npz'


# set file
matrix_to_load = Path(data__dir, matrix_filename).with_suffix(suffix)


# to load data
dummy_matrix = scipy.sparse.load_npz(matrix_to_load)
dummy_matrix = dummy_matrix.tocsr()
print(dummy_matrix)


# to initialize matrix
A = PETSc.Mat().create()
A.setSizes(dummy_matrix.shape)
A.setUp()
rstart, rend = A.getOwnershipRange()


# to assign values to indices
A = PETSc.Mat().createAIJ(
    size=dummy_matrix.shape,
    csr=(
        dummy_matrix.indptr[rstart:rend+1] - dummy_matrix.indptr[rstart],
        dummy_matrix.indices[dummy_matrix.indptr[rstart]:dummy_matrix.indptr[rend]],
        dummy_matrix.data[dummy_matrix.indptr[rstart]:dummy_matrix.indptr[rend]],
    ),
)


# to assemble and inspect
A.assemble()
A.view()


# here the actual code starts
b = A.getColumnVector(0)
b.view()
print(type(b))  # class 'petsc4py.PETSc.Vec'
