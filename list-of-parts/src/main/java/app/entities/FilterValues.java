package app.entities;

import java.util.Date;

/**
 * Created by Дмитрий on 23.02.2018.
 * Entity which fields contain values entered by user in filter form in list.jsp
 */
public class FilterValues extends Part {

    private Date shippedAfter;
    private Date shippedBefore;
    private Date receivedAfter;
    private Date receivedBefore;
    private String sortBy;          //sort by column (1 to 6)
    private String sortingOrder;    //ASC or DESC

    public FilterValues (String partName, String partNumber, String vendor, int qty, Date shpdAfter, Date shpdBefore,
                         Date rcvdAfter, Date rcvdBefore, String sortBy, String sortingOrder) {

        this.partName = partName;
        this.partNumber = partNumber;
        this.vendor = vendor;
        this.qty = qty;
        shippedAfter = shpdAfter;
        shippedBefore = shpdBefore;
        receivedAfter = rcvdAfter;
        receivedBefore = rcvdBefore;
        this.sortBy = sortBy;
        this.sortingOrder = sortingOrder;
    }

    public Date getShippedAfter() {
        return shippedAfter;
    }

    public void setShippedAfter(Date shippedAfter) {
        this.shippedAfter = shippedAfter;
    }

    public Date getShippedBefore() {
        return shippedBefore;
    }

    public void setShippedBefore(Date shippedBefore) {
        this.shippedBefore = shippedBefore;
    }

    public Date getReceivedAfter() {
        return receivedAfter;
    }

    public void setReceivedAfter(Date receivedAfter) {
        this.receivedAfter = receivedAfter;
    }

    public Date getReceivedBefore() {
        return receivedBefore;
    }

    public void setReceivedBefore(Date receivedBefore) {
        this.receivedBefore = receivedBefore;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(String sortingOrder) {
        this.sortingOrder = sortingOrder;
    }
}
