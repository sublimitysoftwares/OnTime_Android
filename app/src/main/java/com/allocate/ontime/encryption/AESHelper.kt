package com.allocate.ontime.encryption

import android.util.Base64
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.security.GeneralSecurityException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created by Sublimity on 05/03/2024.
 */

class AESHelper private constructor() {
    private val characterEncoding = "UTF-8"
    private val cipherTransformation = "AES/CBC/PKCS5Padding"
    private val aesEncryptionAlgorithm = "AES"

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    fun decrypt(cipherText: ByteArray?, key: ByteArray?, initialVector: ByteArray?): ByteArray? {
        var cipherText = cipherText
        val cipher = Cipher.getInstance(cipherTransformation)
        val secretKeySpecy = SecretKeySpec(key, aesEncryptionAlgorithm)
        val ivParameterSpec = IvParameterSpec(initialVector)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy, ivParameterSpec)
        cipherText = cipher.doFinal(cipherText)
        return cipherText
    }

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    fun encrypt(plainText: ByteArray?, key: ByteArray?, initialVector: ByteArray?): ByteArray? {
        var plainText = plainText
        val cipher = Cipher.getInstance(cipherTransformation)
        val secretKeySpec = SecretKeySpec(key, aesEncryptionAlgorithm)
        val ivParameterSpec = IvParameterSpec(initialVector)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
        plainText = cipher.doFinal(plainText)
        return plainText
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getKeyBytes(key: String): ByteArray {
        val keyBytes = ByteArray(16)
        val parameterKeyBytes = key.toByteArray(charset(characterEncoding))
        System.arraycopy(
            parameterKeyBytes,
            0,
            keyBytes,
            0,
            Math.min(parameterKeyBytes.size, keyBytes.size)
        )
        return keyBytes
    }

    /// <summary>
    /// Encrypts plaintext using AES 128bit key and a Chain Block Cipher and returns a base64 encoded string
    /// </summary>
    /// <param name="plainText">Plain text to encrypt</param>
    /// <param name="key">Secret key</param>
    /// <returns>Base64 encoded string</returns>
    @Throws(
        UnsupportedEncodingException::class,
        InvalidKeyException::class,
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    fun encrypt(key: String, plainText: String): String {
        val plainTextbytes = plainText.toByteArray(charset(characterEncoding))
        val keyBytes = getKeyBytes(key)
        return Base64.encodeToString(encrypt(plainTextbytes, keyBytes, keyBytes), Base64.DEFAULT)
    }

    /// <summary>
    /// Decrypts a base64 encoded string using the given key (AES 128bit key and a Chain Block Cipher)
    /// </summary>
    /// <param name="encryptedText">Base64 Encoded String</param>
    /// <param name="key">Secret Key</param>
    /// <returns>Decrypted String</returns>
    @Throws(GeneralSecurityException::class, IOException::class)
    fun decrypt(key: String, encryptedText: String?): String {
        val cipheredBytes = Base64.decode(encryptedText, Base64.DEFAULT)
        val keyBytes = getKeyBytes(key)
        return String(decrypt(cipheredBytes, keyBytes, keyBytes)!!, charset(characterEncoding))
    }

    companion object {
        private val INSTANCE = AESHelper()

        @JvmStatic
        fun getInstance(): AESHelper {
            return INSTANCE
        }
    }
}
