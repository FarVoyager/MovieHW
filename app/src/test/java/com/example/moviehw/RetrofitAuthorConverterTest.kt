package com.example.moviehw

import com.example.moviehw.data.retrofit.Author
import com.example.moviehw.data.retrofit.AuthorResponse
import com.example.moviehw.data.retrofit.converter.AuthorResponseConverter
import org.junit.Test
import org.junit.Assert

class RetrofitAuthorConverterTest {

    private val testConverter = AuthorResponseConverter()

    @Test
    fun authorConverterBaseTest() {
        val apiResponse = AuthorResponse(
            "57",
            "tester666",
            "http://url.com/eh65jw94jrv83hu",
            "http://url.com/repos",
            "http://url.com/subs"
        )
        val test = testConverter.convert(apiResponse)
        val expectedResult = Author(
            "57",
            "tester666",
            "http://url.com/eh65jw94jrv83hu",
            "http://url.com/repos",
            "http://url.com/subs"
        )
        Assert.assertEquals(expectedResult, test)
    }

    @Test
    fun authorConverterNullPropertyTest() {
        val apiResponse = AuthorResponse(
            "57",
            "tester666",
            null,
            "http://url.com/repos",
            "http://url.com/subs"
        )
        val test = testConverter.convert(apiResponse)
        val expectedResult = null
        Assert.assertEquals(expectedResult, test)
    }

}