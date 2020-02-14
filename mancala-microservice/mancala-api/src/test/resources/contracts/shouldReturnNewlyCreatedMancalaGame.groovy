import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.http.MediaType

Contract.make {

    description "should return a newly created Mancala Game "
    request {
        url("/games")
        method(HttpMethods.HttpMethod.POST)
    }
    response {
        status(200)
        body(
                ["id"        : "5d414dcd24e4990006e7c900",
                 "pits"      : [
                         [
                                 "id"    : 1,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 2,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 3,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 4,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 5,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 6,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 7,
                                 "stones": 0
                         ],
                         [
                                 "id"    : 8,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 9,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 10,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 11,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 12,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 13,
                                 "stones": 6
                         ],
                         [
                                 "id"    : 14,
                                 "stones": 0
                         ]
                 ]
                 ,
                 "playerTurn": null
                ]

        )
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}