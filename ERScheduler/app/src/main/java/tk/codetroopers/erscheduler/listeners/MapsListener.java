package tk.codetroopers.erscheduler.listeners;


import android.location.Location;

public interface MapsListener {
    void onCheckLocationPermissionsSuccess();
    void onCheckLocationPermissionsFailure();
    void onFetchUserLocationSuccess(Location location);
    void onFetchUserLocationFailure();
    void onGpsDisabled();
}
