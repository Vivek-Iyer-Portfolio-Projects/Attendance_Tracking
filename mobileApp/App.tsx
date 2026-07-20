import React, { useState, useEffect } from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  View,
  StatusBar,
  Alert
} from 'react-native';
import { useLocationVerification } from './useLocationVerification';

const App = () => {
  const [employeeId, setEmployeeId] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // Bring in our custom hardware logic
  const { location, wifiState, isScanning, error, scanHardware } = useLocationVerification();

  // Automatically scan hardware the moment the user logs in
  useEffect(() => {
    if (isLoggedIn) {
      scanHardware();
    }
  }, [isLoggedIn, scanHardware]);

  const handleLogin = () => {
    if (employeeId.trim().length > 0) {
      setIsLoggedIn(true);
    }
  };

  const handleClockIn = () => {
    if (error) {
      Alert.alert("Verification Failed", error);
      return;
    }
    if (!location || !wifiState) {
      Alert.alert("Hold on", "Still acquiring hardware signals...");
      return;
    }

    // This perfectly matches the Java DTO your Spring Boot API expects!
    const payload = {
      employeeId: employeeId,
      latitude: location.latitude,
      longitude: location.longitude,
      bssid: wifiState.bssid,
      ssid: wifiState.ssid
    };

    console.log("PAYLOAD READY FOR SPRING BOOT:", payload);
    Alert.alert("Secure Payload Generated", `Check your terminal to see the data for ${employeeId}!`);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setEmployeeId('');
  };

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="dark-content" />
      
      {!isLoggedIn ? (
        <View style={styles.card}>
          <Text style={styles.headerTitle}>Secure Portal</Text>
          <Text style={styles.subtitle}>Enter your Employee ID to continue</Text>
          
          <TextInput
            style={styles.input}
            placeholder="e.g. EMP123"
            placeholderTextColor="#999"
            value={employeeId}
            onChangeText={setEmployeeId}
            autoCapitalize="characters"
          />
          
          <TouchableOpacity style={styles.primaryButton} onPress={handleLogin}>
            <Text style={styles.buttonText}>Authenticate</Text>
          </TouchableOpacity>
        </View>
      ) : (
        <View style={styles.card}>
          <Text style={styles.headerTitle}>Welcome, {employeeId}</Text>
          <Text style={styles.subtitle}>Verifying location requirements</Text>
          
          <View style={styles.statusBox}>
            {error ? (
              <Text style={[styles.statusText, { color: '#D32F2F' }]}>⚠️ {error}</Text>
            ) : (
              <>
                <Text style={styles.statusText}>
                  📍 GPS: {isScanning ? 'Acquiring satellites...' : location ? `${location.latitude.toFixed(5)}, ${location.longitude.toFixed(5)}` : 'Waiting...'}
                </Text>
                <Text style={styles.statusText}>
                  📶 Wi-Fi: {isScanning ? 'Scanning...' : wifiState?.ssid ? `${wifiState.ssid} (${wifiState.bssid})` : 'Waiting...'}
                </Text>
              </>
            )}
          </View>

          <TouchableOpacity 
            style={[styles.clockInButton, (isScanning || error) && styles.disabledButton]} 
            onPress={handleClockIn}
          >
            <Text style={styles.buttonText}>Secure Clock In</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.secondaryButton} onPress={handleLogout}>
            <Text style={styles.secondaryButtonText}>Log Out</Text>
          </TouchableOpacity>
        </View>
      )}
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#F4F7F9', justifyContent: 'center', padding: 20 },
  card: { backgroundColor: '#FFFFFF', padding: 24, borderRadius: 12, shadowColor: '#000', shadowOffset: { width: 0, height: 2 }, shadowOpacity: 0.1, shadowRadius: 4, elevation: 3 },
  headerTitle: { fontSize: 24, fontWeight: 'bold', color: '#1A1A1A', marginBottom: 8 },
  subtitle: { fontSize: 14, color: '#666', marginBottom: 24 },
  input: { borderWidth: 1, borderColor: '#E0E0E0', borderRadius: 8, padding: 14, fontSize: 16, marginBottom: 20, color: '#333' },
  primaryButton: { backgroundColor: '#0052CC', padding: 16, borderRadius: 8, alignItems: 'center' },
  clockInButton: { backgroundColor: '#00875A', padding: 16, borderRadius: 8, alignItems: 'center', marginBottom: 12 },
  disabledButton: { backgroundColor: '#A5D6A7' }, // Lighter green when scanning or errored
  secondaryButton: { padding: 16, alignItems: 'center' },
  buttonText: { color: '#FFFFFF', fontSize: 16, fontWeight: '600' },
  secondaryButtonText: { color: '#555', fontSize: 16, fontWeight: '600' },
  statusBox: { backgroundColor: '#F0F5FF', padding: 16, borderRadius: 8, marginBottom: 24 },
  statusText: { fontSize: 14, color: '#333', marginVertical: 4 }
});

export default App;