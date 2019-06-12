package com.stakater.nordmart.catalog.common;

import javax.servlet.http.HttpServletRequest;

public class IstioHeaders
{
    private final String requestId;
    private final String b3TraceId;
    private final String b3SpanId;
    private final String b3ParentSpanId;
    private final String b3Sampled;
    private final String b3Flags;
    private final String otSpanId;

    public IstioHeaders(HttpServletRequest request)
    {
        if (request != null)
        {
            this.requestId = request.getHeader("x-request-id");
            this.b3TraceId = request.getHeader("x-b3-traceid");
            this.b3SpanId = request.getHeader("x-b3-spanid");
            this.b3ParentSpanId = request.getHeader("x-b3-parentspanid");
            this.b3Sampled = request.getHeader("x-b3-sampled");
            this.b3Flags = request.getHeader("x-b3-flags");
            this.otSpanId = request.getHeader("x-ot-span-context");
        }
        else
        {
            this.requestId = null;
            this.b3TraceId = null;
            this.b3SpanId = null;
            this.b3ParentSpanId = null;
            this.b3Sampled = null;
            this.b3Flags = null;
            this.otSpanId = null;
        }
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
            ", otSpanId='" + otSpanId + '\'' +
            '}';
    }
}
