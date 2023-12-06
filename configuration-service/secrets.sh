export VAULT_TOKEN="00000000-0000-0000-0000-000000000000"
export VAULT_ADDR="http://127.0.0.1:8200"

vault kv put dev/order-service spring.datasource.username=order spring.datasource.password=postgres jwt.public-key=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArergYCFqNNq/aSh2KsToaVJfR8Wzu6kDMMW+gb/X7WlOl1NIBUcvUOR14F8fh0vIM9E/sEvczJD2uAC7xONLBJ0tBtKdrgv/GlYGSei55fclkrK5S8oGqeyoseOVY/8Ne4iCGhpR7LEI0JQS5MjFEJOxqV8DsmWI1xhNeGj/YPg9upgMCmcsvZJAFsCGFRe6Aopri61WAl/lkwU+qnscJXsQy+hZ9ur30gfl9BF6KtVTsMQz0NLuapjflSOAlEnBFCVyutuqK11pW3YE2zRn/t5WHmruk8vOghg8+k2+Zj2TIrsvqp91+/luFGnB6ywWN/8Ry32K6ifZe0hP9eWzHQIDAQAB
vault kv put dev/product-service spring.data.mongodb.username=product spring.data.mongodb.password=password jwt.public-key=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArergYCFqNNq/aSh2KsToaVJfR8Wzu6kDMMW+gb/X7WlOl1NIBUcvUOR14F8fh0vIM9E/sEvczJD2uAC7xONLBJ0tBtKdrgv/GlYGSei55fclkrK5S8oGqeyoseOVY/8Ne4iCGhpR7LEI0JQS5MjFEJOxqV8DsmWI1xhNeGj/YPg9upgMCmcsvZJAFsCGFRe6Aopri61WAl/lkwU+qnscJXsQy+hZ9ur30gfl9BF6KtVTsMQz0NLuapjflSOAlEnBFCVyutuqK11pW3YE2zRn/t5WHmruk8vOghg8+k2+Zj2TIrsvqp91+/luFGnB6ywWN/8Ry32K6ifZe0hP9eWzHQIDAQAB
vault kv put dev/user-service jwt.public-key=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArergYCFqNNq/aSh2KsToaVJfR8Wzu6kDMMW+gb/X7WlOl1NIBUcvUOR14F8fh0vIM9E/sEvczJD2uAC7xONLBJ0tBtKdrgv/GlYGSei55fclkrK5S8oGqeyoseOVY/8Ne4iCGhpR7LEI0JQS5MjFEJOxqV8DsmWI1xhNeGj/YPg9upgMCmcsvZJAFsCGFRe6Aopri61WAl/lkwU+qnscJXsQy+hZ9ur30gfl9BF6KtVTsMQz0NLuapjflSOAlEnBFCVyutuqK11pW3YE2zRn/t5WHmruk8vOghg8+k2+Zj2TIrsvqp91+/luFGnB6ywWN/8Ry32K6ifZe0hP9eWzHQIDAQAB jwt.private-key=MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCt6uBgIWo02r9pKHYqxOhpUl9HxbO7qQMwxb6Bv9ftaU6XU0gFRy9Q5HXgXx+HS8gz0T+wS9zMkPa4ALvE40sEnS0G0p2uC/8aVgZJ6Lnl9yWSsrlLygap7Kix45Vj/w17iIIaGlHssQjQlBLkyMUQk7GpXwOyZYjXGE14aP9g+D26mAwKZyy9kkAWwIYVF7oCimuLrVYCX+WTBT6qexwlexDL6Fn26vfSB+X0EXoq1VOwxDPQ0u5qmN+VI4CUScEUJXK626orXWlbdgTbNGf+3lYeau6Ty86CGDz6Tb5mPZMiuy+qn3X7+W4UacHrLBY3/xHLfYrqJ9l7SE/15bMdAgMBAAECggEAL8JZPWzPqpYLjEglMxeBrzKEocQD2FvQWXtkFmNlW3fYsiuXkoN61d73E9drZA8GelxS/gnHJ5IDfoINDkNQnYLAE6iQznPS6RuIfJFhQ2p8Me3MfqiK7ezkYn1Zz+0BedQBzkM+Dh6LiiOvkI4lt6inWBiUfk/tpQuycod64di0IIVgK8KPqdGtQQY4+w3L/95rHX0elGGTTL652rsBl3Wgv7d8vsB9524jvSP2LIpo5I411WJJ7JRcgNL5llYEro3d+WZEBpt+e2jh84jIBzJOL1+6QW6wmAISvQ/G0PpA444UZvex98ptrZU9LLRlhr3Ga0wbGhhgEhvTQ3vxoQKBgQD6KGh7vnxGPpCkiv6wnBY8q80Bqr8nCYiRcmocKy93k9OoaAmm4mrSDm1JNLtqz3yT9asLTf7BNTRVm8CPHVlGKdTTeHhBTgrDpAxAQEB9RxGZ4qG7fon7WyrU94u2G9Okz/LMak9GklRLHRp6rvgS6O2HC2tiVEs5jaXIvsS9aQKBgQCx+qiFDqRP2i5bbPuLAMS8UNv0OnTnVHH/ReqvM09Hnmyr5SdlFU7Zl62DrgAq3CumnFfecwmrwJ511/6cfycPRQgVWR2la6HrcmBCxYCatHdpJvoCZ3nejN2T8tj2041WfidsPZEFleC+xJJdaYAY1CZHd4hCeKblZPAh3FgtlQKBgQCv8mHiv0zLX0/+22BdubsOO0v64yyFeXgbzNyDKYC+WzQgm+RGA7RcQBdvyO4VJxCZRM5kKhyHqjNAD0d1oY/cAKoXfwEd9ZUum6CeRFLXinf/w1478Ll+GFZc6ivTD2lh330Hd7KAkYVGO5cXOR90tW3zcP27cExkk5NKSZs5GQKBgE0cFmsfft0B4FfIDeuZd6NHPSTBNukK0yEQP6+MgWq8HsUS0VCgPLtVVd3ZQ0VOgrL6xa8hFC2PlJfl7vAKvveEpw3owEltKogB0+bZ/vAIBDxQoLzt7AUZ7t4stwepfHDe02G4At7yop7EN8206cTYFYWdKjHyaAy3ALsK5kFJAoGAKYKj3A/TqQY8xId35NsD8sE2VzGKs52gdO7pfLV5f6M0LjZ7WE1eTDYoYaI80tnoI62cY/YJWUyo928/Ko5WK43AB6XDj0DnrsCVeubzSCKFu9H2BjvlLZO/yLt1QyXBPvVFHhyNk/3mOHjQAr7qfpJV2vSfwvt4VCyD4zP2wAM= spring.datasource.username=user spring.datasource.password=postgres
