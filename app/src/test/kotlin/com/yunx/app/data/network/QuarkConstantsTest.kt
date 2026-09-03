package com.yunx.app.data.network

import java.net.URI
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QuarkConstantsTest {

    @Test
    fun `login URL requests the PC web experience`() {
        val queryParameters = URI(QuarkConstants.LOGIN_URL).rawQuery
            .split("&")
            .associate { parameter -> parameter.substringBefore("=") to parameter.substringAfter("=") }

        assertEquals("pc", queryParameters["fr"])
        assertEquals("pc", queryParameters["platform"])
    }

    @Test
    fun `login user agent identifies as a generic desktop browser`() {
        val loginUserAgent = QuarkConstants.USER_AGENT

        assertTrue(loginUserAgent.contains("Mozilla/5.0 (Windows NT 10.0; Win64; x64)"))
        assertTrue(loginUserAgent.contains("Chrome/"))
        assertTrue(loginUserAgent.contains("Safari/"))
        assertFalse(loginUserAgent.contains("Quark", ignoreCase = true))
        assertFalse(loginUserAgent.contains("Electron", ignoreCase = true))
        assertFalse(loginUserAgent.contains("Channel/", ignoreCase = true))
    }

    @Test
    fun `API user agent remains separate from web login identity`() {
        assertNotEquals(QuarkConstants.USER_AGENT, QuarkConstants.API_USER_AGENT)
        assertTrue(QuarkConstants.API_USER_AGENT.contains("quark-cloud-drive", ignoreCase = true))
    }
}
