(let* ((input 368078)
       (layer-ends (map 'list
                        #'(lambda (i)
                            (cons i
                                  (+ 1
                                     (loop for j from 0 upto i
                                           sum (* 8 j)))))
                        (loop for n from 0 below 1000 collect n)))
       (layer-containing (find-if #'(lambda (l) (> (cdr l) input)) layer-ends))
       )
  layer-containing
  )

;; layer-containing = (303 . 368449)
;; 368449 - 303 = 368146, center of layer side
;; 368146 - 368078 = 68, distance to center
;; 303 + 68 = 371 steps
