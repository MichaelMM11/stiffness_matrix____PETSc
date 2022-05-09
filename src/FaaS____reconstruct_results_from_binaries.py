#!/usr/bin/env python3
# -*- coding:utf-8 -*-

"""
purpose
    - to demonstrate that imported vector/matrix solve yield the same result

workflow
    1) load provided matrix A, vector b both as binaries
    2) solve with ksp

requirement
    - file, filename of matrix in binary format (createBinary method)
    - file, filename of vector in binary format (createBinary method)

remark
    - data (matrix, vector) is assumed to be available in binary format
    - no POSIX path is accepted, therefore the str() transformation
    - code closely related to SSoT____mimik_Comsol.py

source
    - https://bitbucket.org/petsc/petsc4py/src/maint/demo/binary-io/matvecio.py
    - https://lists.mcs.anl.gov/mailman/htdig/petsc-users/2016-June/029691.html
"""

from pathlib import Path
from petsc4py import PETSc


# to set directory dependencies
current__dir = Path.cwd()
main__dir = current__dir.parents[0]
data__dir = main__dir.joinpath('data/processed')


# to select stiffness matrix, load vector from binaries
matrix_filename = 'matrix-A'
vector_filename = 'vector-b'
suffix = '.dat'
matrix_file = Path(data__dir, matrix_filename).with_suffix(suffix)
vector_file = Path(data__dir, vector_filename).with_suffix(suffix)


# to load data from binaries
## str because POSIX is not accepted
viewer = PETSc.Viewer().createBinary(str(matrix_file), 'r')
A = PETSc.Mat().load(viewer)
viewer = PETSc.Viewer().createBinary(str(vector_file), 'r')
b = PETSc.Vec().load(viewer)


# to create solution vector
N_POINTS = b.getSize()
x = PETSc.Vec().createSeq(N_POINTS)


# to solve Ax=b
ksp = PETSc.KSP().create()
ksp.setOperators(A)
ksp.setFromOptions()
chosen_solver = ksp.getType()
print(f'Solving with: {chosen_solver:}')
ksp.solve(b, x)
current_solution = x.getArray()
x.setArray(current_solution)
x.view()
