package sample.app.task.utils;

import sample.app.task.R;

public class IconUtils {
    public static int getIcon(String iconName) {
        switch (iconName) {
            case "apartment":
                return R.drawable.apartment;
            case "condo":
                return R.drawable.condo;
            case "boat":
                return R.drawable.boat;
            case "land":
                return R.drawable.land;
            case "swimming":
                return R.drawable.swimming;
            case "garden":
                return R.drawable.garden;
            case "garage":
                return R.drawable.garage;
            case "rooms":
                return R.drawable.rooms;
            case "no-room":
                return R.drawable.no_room;
            default:
                return R.mipmap.ic_launcher;
        }
    }
}
