package amazon;

/**
 * Created by User on 27.08.2016.
 */
public class ItemLookup {

    public String itemGetterPrise(String asin){
        String url=null;
        RequestToAmazon requestToAmazon=new RequestToAmazon();
        requestToAmazon.setOperations("ItemLookup");
        requestToAmazon.setItemId(asin);
        requestToAmazon.setResponseGroup("Large");
        url = requestToAmazon.itemRequest();
        System.out.println(url);
        return url;
    }
    public String itemAttributes(String asin){
        String url=null;
        RequestToAmazon requestToAmazon=new RequestToAmazon();
        requestToAmazon.setOperations("ItemLookup");
        requestToAmazon.setItemId(asin);
        requestToAmazon.setResponseGroup("ItemAttributes");
        url = requestToAmazon.itemRequest();
      //  System.out.println(url);
        return url;
    }
    public String itemImage(String asin){
        String url=null;
        RequestToAmazon requestToAmazon=new RequestToAmazon();
        requestToAmazon.setOperations("ItemLookup");
        requestToAmazon.setItemId(asin);
        requestToAmazon.setResponseGroup("Images");
        url = requestToAmazon.itemRequest();
     //   System.out.println(url);
        return url;
    }

}
