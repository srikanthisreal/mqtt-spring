package contracts

Contract.make {
    request {
        method 'GET'
        url '/locations/1'
    }
    response {
        status 200
        body("""
            {
                "id": 1,
                "name": "Test Location",
                "description": "This is a test location"
            }
        """)
        headers {
            contentType(applicationJson())
        }
    }
}
