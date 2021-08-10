package simple.program.cryptonotifyer.data.remote.response

class CryptoListResponse : ArrayList<CryptoListResponse.CryptoListResponseItem>(){
    data class CryptoListResponseItem(
        val id: String = "",
        val name: String = "",
        val symbol: String = ""
    )
}