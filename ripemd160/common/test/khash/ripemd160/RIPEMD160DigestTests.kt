package khash.ripemd160

import khash.extensions.hexToByteArray
import khash.ripemd160.Ripemd160Digest
import khash.ripemd160.extensions.digestRipemd160
import kotlin.test.Test
import kotlin.test.assertTrue

class RIPEMD160DigestTests {

    // from http://homes.esat.kuleuven.be/~bosselae/ripemd160.html#Outline
    private val testVectors = arrayOf(
        "" to "9c1185a5c5e9fc54612808977ee8f548b2258d31",
        "a" to "0bdc9d2d256b3ee9daae347be6f4dc835a467ffe",
        "abc" to "8eb208f7e05d987a9b044a8e98c6b087f15a0bfc",
        "message digest" to "5d0689ef49d2fae572b881b123a85ffa21595f36",
        "abcdefghijklmnopqrstuvwxyz" to "f71c27109c692c1b56bbdceb5b9d2865b3708dbc",
        "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq" to "12a053384a9c0c88e405a06c27dcf49ada62eb2b",
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789" to "b0e20b6e3116640286ed3a87a5713079b21f5189",
        "1234567890".repeat(8) to "9b752e45573d4b39f4dbd3323cab82bf63326bfb",
        "a".repeat(1_000_000) to "52783243c1697bdbe16d37f97f68f08325dc1528"
    )

    @Test
    fun testStringInput() {
        testVectors.forEach { (message, expectedDigest) ->
            assertTrue(message.digestRipemd160().contentEquals(expectedDigest.hexToByteArray()))
        }
    }

    @Test
    fun testByteInput() {
        val foo = Ripemd160Digest()
        repeat((1..1_000_000).count()) {
            foo.update('a'.toByte())
        }

        val result = ByteArray(Ripemd160Digest.DIGEST_LENGTH)
        foo.doFinal(result, 0)

        assertTrue(result.contentEquals("52783243c1697bdbe16d37f97f68f08325dc1528".hexToByteArray()))
    }
}
