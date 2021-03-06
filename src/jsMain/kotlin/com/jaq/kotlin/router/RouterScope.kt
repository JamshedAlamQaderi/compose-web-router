package com.jaq.kotlin.router

import androidx.compose.runtime.Composable
import com.jaq.kotlin.context.Context
import com.jaq.kotlin.context.RouteContext
import com.jaq.kotlin.parser.Parser

class RouterScope(path: String) : Route {
    private val _path = path
    private val routes = arrayListOf<Route>()

    fun router(path: String, child: RouterScope.() -> Unit) {
        val routerScope = RouterScope(path)
        routerScope.child()
        routes.add(routerScope)
    }

    fun routeView(
        path: String,
        view: @Composable() (RouterViewScope.(Context) -> Unit)? = null
    ) {
        val routeViewScope = RouterViewScope(path, view)
        routes.add(routeViewScope)
    }

    override fun isRenderer() = false

    override fun <T> render(browserUrl: String, parentUrl: String, parser: Parser<T>): RouteModel? {
        val routerUrl = parentUrl + _path
        return routes.firstNotNullOfOrNull { it.render(browserUrl, routerUrl, parser) }
    }

}