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

    public FilterValues (String partName, String partNumber, String vendor, int qty, Date shpdAfter, Date shpdBefore,
                         Date rcvdAfter, Date rcvdBefore) {

        this.partName = partName;
        this.partNumber = partNumber;
        this.vendor = vendor;
        this.qty = qty;
        shippedAfter = shpdAfter;
        shippedBefore = shpdBefore;
        receivedAfter = rcvdAfter;
        receivedBefore = rcvdBefore;
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
}
