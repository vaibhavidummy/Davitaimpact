package in.davita.impact.erp.davitaauthserver.filter;

import com.netflix.zuul.ZuulFilter;

/**
 * @Author : Prashant Wankhede on 30-04-2021
 */
public class PostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
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
        System.out.println("Inside Response Filter");

        return null;
    }
}