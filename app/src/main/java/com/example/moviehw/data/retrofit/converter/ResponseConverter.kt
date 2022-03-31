package com.example.moviehw.data.retrofit.converter

interface ResponseConverter<Domain, Response>  {

    fun convert(response: Response): Domain
}