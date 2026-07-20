import { useState, useCallback } from 'react';
import Geolocation from '@react-native-community/geolocation';
import NetInfo from '@react-native-community/netinfo';

export const useLocationVerification = () => {
  const [location, setLocation] = useState<{ latitude: number; longitude: number } | null>(null);
  const [wifiState, setWifiState] = useState<{ ssid: string | null; bssid: string | null } | null>(null);
  const [isScanning, setIsScanning] = useState(false);
  const [error, setError] = useState(null);

  const scanHardware = useCallback(() => {
    setIsScanning(true);
    setError(null);

    // 1. Extract Wi-Fi Information
    NetInfo.fetch().then(state => {
      if (state.type === 'wifi') {
        setWifiState({
          ssid: state.details?.ssid || 'Hidden Network',
          bssid: state.details?.bssid || 'Unknown MAC',
        });
      } else {
        setWifiState(null);
        setError('Device is not connected to Wi-Fi. Please connect to the corporate network.');
      }
    });

    // 2. Request iOS Permissions and Extract GPS
    Geolocation.requestAuthorization(); 
    Geolocation.getCurrentPosition(
      position => {
        setLocation({
          latitude: position.coords.latitude,
          longitude: position.coords.longitude,
        });
        setIsScanning(false);
      },
      err => {
        console.error('GPS Error:', err);
        setError('Failed to acquire GPS signal. Ensure location services are enabled.');
        setIsScanning(false);
      },
      { enableHighAccuracy: true, timeout: 15000, maximumAge: 10000 }
    );
  }, []);

  return { location, wifiState, isScanning, error, scanHardware };
};