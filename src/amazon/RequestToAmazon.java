package amazon;

import java.util.HashMap;
import java.util.Map;



public class RequestToAmazon extends Instance {

    public String itemRequest() {
        String respond = null;
        SignedRequest signedRequest = new SignedRequest().invoke();
        if (signedRequest.is()) ;
        SignedRequestsHelper helper = signedRequest.getHelper();
        String requestUrl = null;
        Map<String, String> params = new HashMap<String, String>();
        params.put("AWSAccessKeyId", Instance.AWS_ACCESS_KEY_ID);
        params.put("AssociateTag", "alshfu86.blogspot.com");
        params.put("Operation", operations);
        if(itemId!=null){
            params.put("ItemId",itemId);
        }
        if(browseNode!=null){
            params.put("BrowseNode",browseNode);
        }
        if (keywords!=null) {
            params.put("Keywords", keywords);
        }
        if (responseGroup!=null) {
            params.put("ResponseGroup", responseGroup);
        }
        if (searchIndex!=null) {
            params.put("SearchIndex", searchIndex);
        }
        if (variationPage!=1) {
            params.put("VariationPage", String.valueOf(variationPage));
        }
        if (sort != null) {
            params.put("Sort", sort);
        }
        if (tile != null) {
            params.put("Title", tile);
        }
        if (availability != null) {
            params.put("Availability", availability);
        }
        if (manufacturer != null) {
            params.put("Manufacturer", manufacturer);
        }
        if (maximumPrice != 10000) {
            params.put("MaximumPrice", String.valueOf(maximumPrice));
        }
        if (minimumPrice != 0) {
            params.put("MinimumPrice", String.valueOf(minimumPrice));
        }
        if (relatedItemPage != 1) {
            params.put("RelatedItemPage", String.valueOf(relatedItemPage));
        }
        if (title != null) {
            params.put("Title", title);
        }
        requestUrl = helper.sign(params);
        return requestUrl;
    }

    private static class SignedRequest {
        private boolean myResult;
        private SignedRequestsHelper helper;

        boolean is() {
            return myResult;
        }

        public SignedRequestsHelper getHelper() {
            return helper;
        }

        public SignedRequest invoke() {
            try {
                helper = SignedRequestsHelper.getInstance(Instance.ENDPOINT, Instance.AWS_ACCESS_KEY_ID, Instance.AWS_SECRET_KEY);
            } catch (Exception e) {
                e.printStackTrace();
                myResult = true;
                return this;
            }
            myResult = false;
            return this;
        }
    }
}

