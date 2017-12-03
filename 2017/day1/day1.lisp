(let* ((input-stream (open "./input"))
       (input (read-line input-stream))
       (input-digs (map 'list
                        #'(lambda (c)
                            (- (char-int c) (char-int #\0)))
                        input))
       (num-digs (length input-digs))
       (matches-only (loop
                       for i from 0 below num-digs
                       collect (let ((dig (elt input-digs i))
                                     (dig-next (elt input-digs (mod (+ i 1) num-digs))))
                                 (if (= dig dig-next)
                                     dig
                                     0))))
       (result (reduce #'+ matches-only)))
  result)

