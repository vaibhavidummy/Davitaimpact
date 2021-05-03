package in.davita.impact.erp.davitaauthserver.filter;

import com.netflix.zuul.ZuulFilter;

/**
 * @Author : Prashant Wankhede on 30-04-2021
 */
public class RouteFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("Inside Route Filter");
        return null;
    }
}