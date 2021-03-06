package com.jaq.kotlin.router

import androidx.compose.runtime.Composable
import com.jaq.kotlin.context.Context
import com.jaq.kotlin.context.RouteContext

data class RouteModel(val path: String, val view: @Composable() (RouterViewScope.(Context) -> Unit)? = null)