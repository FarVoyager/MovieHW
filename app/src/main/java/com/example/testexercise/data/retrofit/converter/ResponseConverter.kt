package com.example.testexercise.data.retrofit.converter

interface ResponseConverter<Domain, Response>  {

    fun convert(response: Response): Domain
}