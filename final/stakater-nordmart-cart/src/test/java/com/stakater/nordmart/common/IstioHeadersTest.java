package com.stakater.nordmart.common;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


/**
 * Unit test for IstioHeaders.
 */
public class IstioHeadersTest
{
    @Mock
    private HttpServletRequest request;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void HeadersTest()
    {
        when(request.getHeader(Constants.IH_REQUEST_ID)).thenReturn(TestConstants.IHV_REQUEST_ID);
        when(request.getHeader(Constants.IH_TRACE_ID)).thenReturn(TestConstants.IHV_TRACE_ID);
        when(request.getHeader(Constants.IH_SPAN_ID)).thenReturn(TestConstants.IHV_SPAN_ID);
        when(request.getHeader(Constants.IH_PARENT_ID)).thenReturn(TestConstants.IHV_PARENT_ID);
        when(request.getHeader(Constants.IH_SAMPLED)).thenReturn(TestConstants.IHV_SAMPLED);
        when(request.getHeader(Constants.IH_FLAGS)).thenReturn(TestConstants.IHV_FLAGS);
        when(request.getHeader(Constants.IH_SPAN_CONTEXT)).thenReturn(TestConstants.IHV_SPAN_CONTEXT);

        IstioHeaders istioHeaders = new IstioHeaders(request);
        Map headers = istioHeaders.getHeaders();

        assertThat("Request ID is correct", headers.get(Constants.IH_REQUEST_ID).equals(TestConstants.IHV_REQUEST_ID));
        assertThat("Trace ID is correct", headers.get(Constants.IH_TRACE_ID).equals(TestConstants.IHV_TRACE_ID));
        assertThat("Span ID is correct", headers.get(Constants.IH_SPAN_ID).equals(TestConstants.IHV_SPAN_ID));
        assertThat("Parent Span ID is correct", headers.get(Constants.IH_PARENT_ID).equals(TestConstants
            .IHV_PARENT_ID));
        assertThat("Sampled is correct", headers.get(Constants.IH_SAMPLED).equals(TestConstants.IHV_SAMPLED));
        assertThat("Flags are correct", headers.get(Constants.IH_FLAGS).equals(TestConstants.IHV_FLAGS));
        assertThat("Span context is correct", headers.get(Constants.IH_SPAN_CONTEXT).equals(TestConstants
            .IHV_SPAN_CONTEXT));
    }
}