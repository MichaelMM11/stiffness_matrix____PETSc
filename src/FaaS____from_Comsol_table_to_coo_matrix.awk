#!/usr/bin/awk -f

####################################################################################################
#                                                                                                  #
#                                                                                                  #
#  purpose                                                                                         #
#      - to convert table in coo format (default output from COMSOL) to .mtx coo format            #
#                                                                                                  #
#  workflow                                                                                        #
#      # awk                                                                                       #
#      1) counts lines that start with signed digits                                               #
#      2) selects lines that start with signed digits                                              #
#      3) finds maximums of 1st, 2nd column                                                        #
#      4) writes required text and maximum of col #1, col #2, lines-that-start-with-signed-digits  #
#      # sed                                                                                       #
#      5) moves the last two lines on top of file                                                  #
#                                                                                                  #
#  execute                                                                                         #
#      1) awk -f FaaS____from_Comsol_table_to_coo_matrix.awk <FILE> > <DATA>.mtx                   #
#      2) vim <DATA>.mtx -esc '$-1,$m0|wq' --not-a-term                                            #
#                                                                                                  #
#  remark                                                                                          #
#      - other ways like grep, sed either to inconvenient or too slow for large files (said)       #
#                                                                                                  #
#                                                                                                  #
####################################################################################################


# initially counted lines started with '%' (because of Comsol), 'newline' but is sensitive to characters [a-Z], so I decided only lines with numbers are taken into account
# BEGIN{d=0}{if(!/^%/) d++}  # to count lines starting with %
# BEGIN{e=0}{if(!/^./) e++}  # to count empty lines
BEGIN {lines_with_actual_data=0}{
    if (/^-[[:digit:]]+/ || /^+[[:digit:]]+/ || /^[[:digit:]]+/)
    lines_with_actual_data++
}


# to print numbers that start with either '+', '-', [0-9]
{
    if (/^-[[:digit:]]+/ || /^+[[:digit:]]+/ || /^[[:digit:]]+/)
    {print $1, $2, $3}  # {print $1+1, $2+1, $3} the original version had the increment by 1 because of 0-based indexing, but Comsol has 1-based indexing; therefore no need to raise by one; but still useful if any future matrix from anywhere must be converted
}


# to find maximum of col #1, col #2
# in theory only the last line is important because of raising indexing
# so this is overkill and needs to be tested if this causes any time penalty
BEGIN { min = max = "NaN" }{
# '+0' to force type convertion
# otherwise string can win over int
    max_col1 = (NR==1 || $1+0>max_col1 ? $1+0 : max_col1)
    max_col2 = (NR==1 || $2+0>max_col2 ? $2+0 : max_col2)
}


# to print the required text for .mtx matrix representation
END {
    print "%%MatrixMarket matrix coordinate real general"
    print max_col1, max_col2, lines_with_actual_data
}
