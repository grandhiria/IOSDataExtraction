package sg.edu.ntu.medicine.lkc.mobile.search.ios.data;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author LKCUser
 */
@Generated("org.jsonschema2pojo")
public class DataIOS {
    
    @SerializedName("artistId")
    @Expose
    private Integer artistId;
    
 //   @SerializedName("i18n_lang")
   // @Expose
  //  private String i18n_lang;
  
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("trackCensoredName")//title
    @Expose
    private String trackCensoredName; //title
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;

    @SerializedName("description")
    @Expose
    private String description;


    @SerializedName("i18n_lang")
    @Expose
    private List<String> i18nLang = new ArrayList<String>();
    
    @SerializedName("price_i18n_countries")
    @Expose
    private List<Object> priceI18nCountries = new ArrayList<Object>();
    @SerializedName("averageUserRating")
    @Expose
    private Double averageUserRating;
    @SerializedName("sellerUrl")
    @Expose
    private String sellerUrl;

    @SerializedName("currentVersionReleaseDate")
    @Expose
    private String currentVersionReleaseDate;
    @SerializedName("fileSizeBytesNumeric")
    @Expose
    private Long fileSizeBytesNumeric;

    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;

    @SerializedName("userRatingCount")
    @Expose
    private Integer userRatingCount;

    @SerializedName("averageUserRatingForCurrentVersion")
    @Expose
    private Double averageUserRatingForCurrentVersion;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    /**
     *
     * @return The i18n_lang
     */
       /**
    public String getLang() {
        return i18n_lang;
    }

 

    public void setLang(String lang) {
        this.i18n_lang = i18n_lang;
    }

    /**
     *
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return The trackCensoredName
     */
    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    /**
     *
     * @param trackCensoredName The trackCensoredName
     */
    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    /**
     *
     * @return The created
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     *
     * @param created The created
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     *
     * @return The screenshots
     */

    /**
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return The catType

    /**
     *
     * @return The i18nLang
     */
    public List<String> getI18nLang() {
        return i18nLang;
    }

    /**
     *
     * @param i18nLang The i18n_lang
     */
    public void setI18nLang(List<String> i18nLang) {
        this.i18nLang = i18nLang;
    }

    /**
     *
     * @return The catKey
     */

    /**
     *
     * @return The priceI18nCountries
     */
    public List<Object> getPriceI18nCountries() {
        return priceI18nCountries;
    }

    /**
     *
     * @param priceI18nCountries The price_i18n_countries
     */
    public void setPriceI18nCountries(List<Object> priceI18nCountries) {
        this.priceI18nCountries = priceI18nCountries;
    }

    /**
     *
     * @return The downloadsMax
     */
    public Double getAverageUserRating() {
        return averageUserRating;
    }

    /**
     *
     * @param downloadsMax The downloads_max
     */
    public void setAverageUserRating(Double averageUserRating) {
        this.averageUserRating = averageUserRating;
    }

    /**
     *
     * @return The website
     */
    public String getWebsite() {
        return sellerUrl;
    }

    /**
     *
     * @param website The website
     */
    public void setWebsite(String sellerUrl) {
        this.sellerUrl = sellerUrl;
    }

    /**
     *
     * @return The packageName
     */


    /**
     *
     * @return The marketUpdate
     */
    public String getMarketUpdate() {
        return currentVersionReleaseDate;
    }

    /**
     *
     * @param marketUpdate The market_update
     */
    public void setMarketUpdate(String currentVersionReleaseDate) {
        this.currentVersionReleaseDate = currentVersionReleaseDate;
    }

    /**
     *
     * @return The size
     */
    public Long getSize() {
        return fileSizeBytesNumeric;
    }

    /**
     *
     * @param size The size
     */
    public void setSize(Long fileSizeBytesNumeric) {
        this.fileSizeBytesNumeric = fileSizeBytesNumeric;
    }

    /**
     *
     * @return The catInt
     */
 
    /**
     *
     * @return The category
     */
    public String getGenres() {
        return primaryGenreName;
    }

    /**
     *
     * @param genres The genres
     */
    public void setgetGenres(String genres) {
        this.primaryGenreName = primaryGenreName;
    }

    /**
     *
     * @return The price
     */
/*
     *
     * @return The rating
     */
    public Integer getUserRatingCount() {
        return userRatingCount;
    }

    /**
     *
     * @param rating The rating
     */
    public void setUserRatingCount(Integer userRatingCount) {
        this.userRatingCount = userRatingCount;
    }

    /**
     *
     * @return The priceNumeric
     */


    /**
     *
     * @return The downloadsMin
     */
    public Double getaverageUserRatingForCurrentVersion() {
        return averageUserRatingForCurrentVersion;
    }

    /**
     *
     * @param downloadsMin The downloads_min
     */
    public void setDownloadsMin(Double averageUserRatingForCurrentVersion) {
        this.averageUserRatingForCurrentVersion = averageUserRatingForCurrentVersion;
    }

    /**
     *
     * @return The developer
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     *
     * @param developer The developer
     */
    public void setgetArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     *
     * @return The numberRatings
     */

    /**
     *
     * @return The marketUrl
     */
    public String getMarketUrl() {
        return trackViewUrl;
    }

    /**
     *
     * @param marketUrl The market_url
     */
    public void setMarketUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    /**
     *
     * @return The deepLink
     */
   

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}
