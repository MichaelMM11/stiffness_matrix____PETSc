#!/usr/bin/env python3
# -*- coding:utf-8 -*-

"""
purpose
    - to load a matrix as PETSc object

workflow
     1) load .npz file
     2) convert data to csr matrix format
     3) create matrix by these csr values

requirement
    - data must be provided in .npz format

source
    - https://lists.mcs.anl.gov/pipermail/petsc-users/2013-August/018502.html
    - https://lists.mcs.anl.gov/pipermail/petsc-users/2018-February/034411.html
    - https://stackoverflow.com/questions/15442632/scipy-sparse-matrices-as-an-input-for-petsc4py
"""

from scipy.io import mmread
from petsc4py import PETSc

import scipy

dummy_matrix = scipy.sparse.load_npz('4x4_matrix_test.npz')
dummy_matrix = dummy_matrix.tocsr()
print(dummy_matrix)

A = PETSc.Mat().create()
A.setSizes(dummy_matrix.shape)
A.setUp()
rstart, rend = A.getOwnershipRange()

A = PETSc.Mat().createAIJ(
     size=dummy_matrix.shape,
     csr=(
          dummy_matrix.indptr[rstart:rend+1] - dummy_matrix.indptr[rstart],
          dummy_matrix.indices[dummy_matrix.indptr[rstart]:dummy_matrix.indptr[rend]],
          dummy_matrix.data[dummy_matrix.indptr[rstart]:dummy_matrix.indptr[rend]],
     ),
)

A.assemble()
A.view()
print(type(A))  # class 'petsc4py.PETSc.Mat'
