package com.stakater.nordmart.common;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class IstioHeaders
{
    private final String requestId;
    private final String b3TraceId;
    private final String b3SpanId;
    private final String b3ParentSpanId;
    private final String b3Sampled;
    private final String b3Flags;
    private final String otSpanContext;

    IstioHeaders(HttpServletRequest request)
    {
        if (request != null)
        {
            this.requestId = request.getHeader(Constants.IH_REQUEST_ID);
            this.b3TraceId = request.getHeader(Constants.IH_TRACE_ID);
            this.b3SpanId = request.getHeader(Constants.IH_SPAN_ID);
            this.b3ParentSpanId = request.getHeader(Constants.IH_PARENT_ID);
            this.b3Sampled = request.getHeader(Constants.IH_SAMPLED);
            this.b3Flags = request.getHeader(Constants.IH_FLAGS);
            this.otSpanContext = request.getHeader(Constants.IH_SPAN_CONTEXT);
        }
        else
        {
            this.requestId = null;
            this.b3TraceId = null;
            this.b3SpanId = null;
            this.b3ParentSpanId = null;
            this.b3Sampled = null;
            this.b3Flags = null;
            this.otSpanContext = null;
        }
    }

    public Map<String, String> getHeaders()
    {
        Map<String, String> headers = new HashMap<>();
        if (requestId != null)
        {
            headers.put(Constants.IH_REQUEST_ID, requestId);
        }

        if (b3TraceId != null)
        {
            headers.put(Constants.IH_TRACE_ID, b3TraceId);
        }

        if (b3SpanId != null)
        {
            headers.put(Constants.IH_SPAN_ID, b3SpanId);
        }

        if (b3ParentSpanId != null)
        {
            headers.put(Constants.IH_PARENT_ID, b3ParentSpanId);
        }

        if (b3Sampled != null)
        {
            headers.put(Constants.IH_SAMPLED, b3Sampled);
        }

        if (b3Flags != null)
        {
            headers.put(Constants.IH_FLAGS, b3Flags);
        }

        if (otSpanContext != null)
        {
            headers.put(Constants.IH_SPAN_CONTEXT, otSpanContext);
        }

        return headers;
    }


    @Override
    public String toString()
    {
        return "IstioHeaders{" +
            "requestId='" + requestId + '\'' +
            ", b3TraceId='" + b3TraceId + '\'' +
            ", b3SpanId='" + b3SpanId + '\'' +
            ", b3ParentSpanId='" + b3ParentSpanId + '\'' +
            ", b3Sampled='" + b3Sampled + '\'' +
            ", b3Flags='" + b3Flags + '\'' +
            ", otSpanContext='" + otSpanContext + '\'' +
            '}';
    }
}
