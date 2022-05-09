#!/usr/bin/env python3
# -*- coding:utf-8 -*-

"""
purpose
    - to demonstrate to solve the equation Ax=b with A,b given in binaryfiles

workflow
    1) load matrix A
    2) load vector b
    3) solve with ksp

requirement
    - file, filename of matrix in .npz format
    - file, filename of vector in .npz format

remark
    - data (matrix, vector) is assumed to be available in .npz format
"""


from pathlib import Path
from petsc4py import PETSc
from scipy.sparse.linalg import spsolve

import scipy


# to set directory dependencies
current__dir = Path.cwd()
main__dir = current__dir.parents[0]
data__dir = main__dir.joinpath('data/processed')


# to select stiffness matrix, load vector
matrix_filename = 'matrix'
vector_filename = 'vector'
suffix = '.npz'
matrix_file = Path(data__dir, matrix_filename).with_suffix(suffix)
vector_file = Path(data__dir, vector_filename).with_suffix(suffix)



# to load stiffness matrix
dummy_matrix = scipy.sparse.load_npz(matrix_file)
dummy_matrix = dummy_matrix.tocsr()
A = PETSc.Mat().create()
A.setSizes(dummy_matrix.shape)
A.setUp()
rstart, rend = A.getOwnershipRange()
csr = (
     dummy_matrix.indptr[rstart:rend+1] - dummy_matrix.indptr[rstart],
     dummy_matrix.indices[dummy_matrix.indptr[rstart]:dummy_matrix.indptr[rend]],
     dummy_matrix.data[dummy_matrix.indptr[rstart]:dummy_matrix.indptr[rend]],
)
A = PETSc.Mat().createAIJ(
     size=dummy_matrix.shape,
     csr=csr,
)
A.assemble()
# A.view()


# to load load vector
dummy_matrix = scipy.sparse.load_npz(vector_file)
dummy_matrix = dummy_matrix.tocsr()
b = PETSc.Mat().create()
b.setSizes(dummy_matrix.shape)
b.setUp()
rstart, rend = b.getOwnershipRange()
b = PETSc.Mat().createAIJ(
     size=dummy_matrix.shape,
     csr=(
          dummy_matrix.indptr[rstart:rend+1] - dummy_matrix.indptr[rstart],
          dummy_matrix.indices[dummy_matrix.indptr[rstart]:dummy_matrix.indptr[rend]],
          dummy_matrix.data[dummy_matrix.indptr[rstart]:dummy_matrix.indptr[rend]],
     ),
)
b.assemble()
b = b.getColumnVector(0)
# b.view()


# to create solution vector
N_POINTS = b.getSize()
x = PETSc.Vec().createSeq(N_POINTS)
#x.view()


# to solve Ax=b
ksp = PETSc.KSP().create()
ksp.setOperators(A)
ksp.setFromOptions()
chosen_solver = ksp.getType()
print(f'Solving with: {chosen_solver:}')
ksp.solve(b, x)
current_solution = x.getArray()
x.setArray(current_solution)


# to check the numbers
#A.view()
#b.view()
#b.view()


# to save data of vector/matrix as binaries
matrix_filename = 'matrix-A'
vector_filename = 'vector-b'
suffix = '.dat'
matrix_file = Path(data__dir, matrix_filename).with_suffix(suffix)
vector_file = Path(data__dir, vector_filename).with_suffix(suffix)

viewer = PETSc.Viewer().createBinary(str(matrix_file), 'w')
viewer(A)
viewer = PETSc.Viewer().createBinary(str(vector_file), 'w')
viewer(b)
