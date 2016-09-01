package amazon;

/**
 * Created by User on 24.08.2016.
 */
public class Instance {
    static final String AWS_ACCESS_KEY_ID = "AKIAIXKEKZQ62LQ3TPAA";
    static final String AWS_SECRET_KEY = "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";
    static final String ENDPOINT = "ecs.amazonaws.com";
    String operations = null;
    String keywords = null;
    String condition = null;
    String tile = null;
    String availability = null;
    String manufacturer = null;
    int maximumPrice = 10000;
    int minimumPrice = 0;
    int relatedItemPage = 1;
    String title = null;
    int variationPage = 1;
    String searchIndex = null;
    String sort = null;
    String responseGroup = null;
    String browseNode=null;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    String itemId=null;


    public String getBrowseNode() {
        return browseNode;
    }

    public void setBrowseNode(String browseNode) {
        this.browseNode = browseNode;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(int maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public int getRelatedItemPage() {
        return relatedItemPage;
    }

    public void setRelatedItemPage(int relatedItemPage) {
        this.relatedItemPage = relatedItemPage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVariationPage() {
        return variationPage;
    }

    public void setVariationPage(int variationPage) {
        this.variationPage = variationPage;
    }

    public String getSearchIndex() {
        return searchIndex;
    }

    public void setSearchIndex(String searchIndex) {
        this.searchIndex = searchIndex;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getResponseGroup() {
        return responseGroup;
    }

    public void setResponseGroup(String responseGroup) {
        this.responseGroup = responseGroup;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }
}
