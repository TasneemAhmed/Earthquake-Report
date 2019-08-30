package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AndroidEarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public AndroidEarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    //tbe of when sperate
    private static final String LOCATION_SEPARATOR = " of ";
    // If the original location string (i.e. "5km N of Cairo, Egypt") contains
    // a primary location (Cairo, Egypt) and a location offset (5km N of that city)
    // then store the primary location separately from the location offset in 2 Strings,
    // so they can be displayed in 2 TextViews.
    private String primaryLocation;
    private String locationOffset;



    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        // Get the {@link Earhquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item_earthquake.xml layout with the ID magntitude
        TextView magntitudeTextView = (TextView) listItemView.findViewById(R.id.magntitude);
        // Get the magntitude from the current Earthquake object and
        double originalMagntitude = currentEarthquake.getMagntitude();

        //format the magntitude to the 0.0
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(originalMagntitude);
        //set the text to magntitude
        magntitudeTextView.setText(output);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magntitudeTextView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(originalMagntitude);
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // Get the location from the current Earthquake object// Get the original location string from the Earthquake object,
        // which can be in the format of "5km N of Cairo, Egypt" or "Pacific-Antarctic Ridge".
        String originalLocation = currentEarthquake.getLocation();

       if (originalLocation.contains(LOCATION_SEPARATOR))
       {
           String[] parts = originalLocation.split(LOCATION_SEPARATOR);
           locationOffset = parts[0] + LOCATION_SEPARATOR;
           primaryLocation = parts[1];
       }
       else
       {
           locationOffset = getContext().getString(R.string.near_the);
           primaryLocation = originalLocation;
       }
        // Find the TextView in the list_item.xml layout with the ID primary_location and set the text into it
        TextView primaryLocationView  = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);
        // Find the TextView in the list_item.xml layout with the ID location_offset  and set the text into it
        TextView locationOffsetView   = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getDate());

        // Find the TextView in the list_item.xml layout with the ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);

        // Find the TextView in the list_item.xml layout with the ID date
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. ""3 :00 AM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeTextView.setText(formattedTime);

        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }


    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude)
    {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        //to convert the color resource ID into an actual integer color value, and return the result as the return value of the getMagnitudeColor() helper method.
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}

