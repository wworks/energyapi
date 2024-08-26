import streamlit as st
import requests
import pandas as pd
import plotly.express as px

# Config
API_URL = "http://energyapi"  # Pas dit aan als je API elders draait
LOGIN_URL = f"{API_URL}/login/"

st.title("Energie Meetdata Dashboard")

# Login formulier
st.sidebar.header("Inloggen")
name = st.sidebar.text_input("Naam")
password = st.sidebar.text_input("Wachtwoord", type="password")
login_button = st.sidebar.button("Inloggen")

if login_button:
    if name and password:
        response = requests.post(LOGIN_URL, json={"name": name, "password": password})
        if response.status_code == 200:
            token = response.json().get("access_token")
            if token:
                st.session_state["token"] = token
                st.session_state["logged_in"] = True
                st.success("Succesvol ingelogd!")
            else:
                st.error("Kon geen token ontvangen.")
        else:
            st.error(f"Inloggen mislukt: {response.text}")
            st.session_state["logged_in"] = False
    else:
        st.error("Voer zowel naam als wachtwoord in.")

# Controleer of de gebruiker is ingelogd
if st.session_state.get("logged_in"):
    st.sidebar.header("Configuratie")
    data_points = st.sidebar.slider("Aantal datapunten om te laden", 10, 1000, 100)

    # Fetch energy data
    headers = {"Authorization": f"Bearer {st.session_state['token']}"}
    try:
        response = requests.get(f"{API_URL}/energy_data/?limit={data_points}", headers=headers)
        response.raise_for_status()  # Raise an HTTPError for bad responses (4xx or 5xx)
        data = response.json()
    except requests.exceptions.HTTPError as http_err:
        st.error(f"HTTP error occurred: {http_err}")
        data = []
    except requests.exceptions.RequestException as req_err:
        st.error(f"Request error occurred: {req_err}")
        data = []
    except ValueError as json_err:
        st.error(f"JSON decode error: {json_err}")
        data = []

    # Create DataFrame
    df = pd.DataFrame(data)

    # Check if DataFrame is empty
    if df.empty:
        st.error("Geen data beschikbaar om weer te geven.")
    else:
        # Convert timestamp to datetime
        df['timestamp'] = pd.to_datetime(df['timestamp'])

        # Display Data
        st.header("Energie Meetdata")
        st.write(df)

        # Plot usage over time
        st.header("Energieverbruik in de tijd")
        fig_usage = px.line(df, x="timestamp", y="usage", title="Energieverbruik (kWh)", labels={"timestamp": "Tijd", "usage": "Verbruik (kWh)"})
        st.plotly_chart(fig_usage)

        # Plot production over time
        st.header("Energieproductie in de tijd")
        fig_production = px.line(df, x="timestamp", y="production", title="Energieproductie (kWh)", labels={"timestamp": "Tijd", "production": "Productie (kWh)"})
        st.plotly_chart(fig_production)
else:
    st.info("Log in om toegang te krijgen tot het dashboard.")
