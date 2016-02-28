
package com.dnsoftindia.swiperefreshlayoutdemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class MovieData {

    @SerializedName("Search")
    @Expose
    private List<com.dnsoftindia.swiperefreshlayoutdemo.models.Search> Search = new ArrayList<>();
    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("Response")
    @Expose
    private String Response;

    /**
     * No args constructor for use in serialization
     */
    public MovieData() {
    }

    /**
     * @param Search
     * @param totalResults
     * @param Response
     */
    public MovieData(List<Search> Search, String totalResults, String Response) {
        this.Search = Search;
        this.totalResults = totalResults;
        this.Response = Response;
    }

    /**
     * @return The Search
     */
    public List<Search> getSearch() {
        return Search;
    }

    /**
     * @param Search The Search
     */
    public void setSearch(List<Search> Search) {
        this.Search = Search;
    }

    /**
     * @return The totalResults
     */
    public String getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The totalResults
     */
    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return The Response
     */
    public String getResponse() {
        return Response;
    }

    /**
     * @param Response The Response
     */
    public void setResponse(String Response) {
        this.Response = Response;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Search).append(totalResults).append(Response).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MovieData) == false) {
            return false;
        }
        MovieData rhs = ((MovieData) other);
        return new EqualsBuilder().append(Search, rhs.Search).append(totalResults, rhs.totalResults).append(Response, rhs.Response).isEquals();
    }
}
