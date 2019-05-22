package com.revature.filters;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class SampleFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// predicate for whether filter should run
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("Filter running!");
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletResponse servletResponse = context.getResponse();
		servletResponse.addHeader("my-custom-header", "hello");
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
