
package com.dnsoftindia.swiperefreshlayoutdemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.text.WordUtils;

public class Search {

    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Year")
    @Expose
    private String Year;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String Type;
    @SerializedName("Poster")
    @Expose
    private String Poster;

    /**
     * No args constructor for use in serialization
     *
     */
    public Search() {
    }

    /**
     *
     * @param Year
     * @param Type
     * @param Poster
     * @param imdbID
     * @param Title
     */
    public Search(String Title, String Year, String imdbID, String Type, String Poster) {
        this.Title = Title;
        this.Year = Year;
        this.imdbID = imdbID;
        this.Type = Type;
        this.Poster = Poster;
    }

    /**
     *
     * @return
     * The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param Title
     * The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     * The Year
     */
    public String getYear() {
        return Year;
    }

    /**
     *
     * @param Year
     * The Year
     */
    public void setYear(String Year) {
        this.Year = Year;
    }

    /**
     *
     * @return
     * The imdbID
     */
    public String getImdbID() {
        return imdbID;
    }

    /**
     *
     * @param imdbID
     * The imdbID
     */
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    /**
     *
     * @return
     * The Type
     */
    public String getType() {
        return WordUtils.capitalize(Type);
    }

    /**
     *
     * @param Type
     * The Type
     */
    public void setType(String Type) {
        this.Type = WordUtils.capitalize(Type);
    }

    /**
     *
     * @return
     * The Poster
     */
    public String getPoster() {
        return Poster;
    }

    /**
     *
     * @param Poster
     * The Poster
     */
    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Title).append(Year).append(imdbID).append(Type).append(Poster).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Search) == false) {
            return false;
        }
        Search rhs = ((Search) other);
        return new EqualsBuilder().append(Title, rhs.Title).append(Year, rhs.Year).append(imdbID, rhs.imdbID).append(Type, rhs.Type).append(Poster, rhs.Poster).isEquals();
    }

}
