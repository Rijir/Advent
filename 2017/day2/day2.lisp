(quicklisp:quickload 'cl-utilities)

(let* ((input-stream (open "./input"))
       (input-lines (loop while (peek-char t input-stream nil nil)
                          collect (read-line input-stream)))
       (input-table (map 'list
                         #'(lambda (row)
                             (map 'list
                                  #'parse-integer
                                  (cl-utilities:split-sequence #\tab row)))
                         input-lines))
       (row-diffs (map 'list
                       #'(lambda (row)
                           (- (apply #'max row)
                              (apply #'min row)))
                       input-table)))
  (reduce #'+ row-diffs))
