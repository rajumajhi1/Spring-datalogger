Phase 1 Requirements:

Main Component: Create a primary component named DashboardPage. This will be the main container for all other dashboard elements.

State Management: Inside DashboardPage, use React hooks (useState) to manage the following states:

faults: An array to store the fault data from the API (default to an empty array []).

isLoading: A boolean to track when the API call is in progress (default to true).

error: A state to hold any potential error messages (default to null).

API Data Fetching:

Use the useEffect hook to fetch data when the component mounts.

Implement a polling mechanism using setInterval to call the API every 15 seconds.

The API endpoint is /api/faults.

The API returns a JSON array of fault objects. Each object has the following structure:

id: A unique string or number for the fault.

stationName: String (e.g., "BARSOI JN").

faultType: String (e.g., "PointFailure").

faultLevel: String, representing severity (e.g., "SC", "MC", "MM").

timestamp: An ISO 8601 date-time string.

Display for Verification: For this phase, simply render the total count of active faults on the page (e.g., "Current Active Faults: {faults.length}") to confirm that the data fetching and state updates are working correctly. Also, display loading and error messages based on the state.

Phase 2: Displaying Key Performance Indicators (KPIs) & Live Feed
Goal: Build the components to display the most critical, at-a-glance information: the top-level statistics and a feed of the most recent critical faults.

(Copy and paste the following into your AI IDE)

You are an expert front-end developer using React. Based on the foundation from Phase 1 where we are fetching fault data, your task is to create two new components for displaying key information.

Phase 2 Requirements:

Create KpiCards Component:

This component will receive the faults array as a prop.

It should display four individual cards with the following calculated values:

Total Stations: The count of unique stationName values in the faults array.

Stations with Faults: This is the same calculation as "Total Stations" since the API only returns active faults.

Critical Alerts Now: The total count of faults where faultLevel is "SC".

Total Active Faults: The total length of the faults array.

Create LiveFaultFeed Component:

This component will also receive the faults array as a prop.

Its logic should be:

Filter the array to only include faults where faultLevel is "SC".

Sort these critical faults by timestamp in descending order (most recent first).

Take only the top 5 from the sorted list.

Render this list, showing the stationName, faultType, and timestamp for each.

Integration: Add the new KpiCards and LiveFaultFeed components into the DashboardPage component from Phase 1, passing the faults state to them as props.

Phase 3: Visualizing System-Wide Status with a Station Grid
Goal: Create a visual, color-coded grid of all stations to provide an intuitive overview of the entire network's health.

(Copy and paste the following into your AI IDE)

You are an expert front-end developer using React. We will now build a component to visualize the status of all stations. This component will use the faults data fetched in Phase 1.

Phase 3 Requirements:

Create StationStatusGrid Component:

This component receives the faults array as a prop.

Determine Station Status Logic:

First, create a map or object that groups all faults by their stationName.

For each station, determine its most severe fault level. The hierarchy is: SC (critical) > MC (major) > MM (minor). For example, if a station has one MM fault and one SC fault, its overall status is SC.

Rendering Logic:

Get a unique list of all station names involved.

Render a grid of cards, one for each unique station.

The background color of each station's card must be determined by its status:

Red if the status is SC.

Orange if the status is MC.

Yellow if the status is MM.

If a station is known to the system but has no active faults, its card should be Green. (You may need a separate API endpoint or a static list of all possible stations for this).

Each card should display the stationName.

Integration: Add the StationStatusGrid component into the main DashboardPage.

Phase 4: Adding Analytical Insight with Charts
Goal: Implement charts to help users identify trends and common problems by visualizing fault data by type and by location.

(Copy and paste the following into your AI IDE)

You are an expert front-end developer using React. Your task is to add data visualization charts to our dashboard using the Recharts library. The component will analyze the faults data from Phase 1.

Phase 4 Requirements:

Setup: Add the recharts library to the project dependencies.

Create FaultAnalysisCharts Component:

This component receives the faults array as a prop.

It will contain two charts side-by-side.

Chart 1: Faults by Type (Pie Chart):

Process the faults array to count the occurrences of each unique faultType.

The result should be an array of objects like [{ name: 'PointFailure', value: 12 }, { name: 'Mains Failure', value: 5 }].

Use this data to render a Pie or Donut Chart.

Chart 2: Top 5 Problem Stations (Bar Chart):

Process the faults array to count the total number of faults for each stationName.

Sort the stations by their fault count in descending order.

Take the top 5 stations.

Use this data to render a vertical Bar Chart, where the X-axis is the stationName and the Y-axis is the fault count.

Integration: Add the FaultAnalysisCharts component to the DashboardPage.

Phase 5: Implementing Advanced Interactivity
Goal: Enhance the dashboard's usability by adding browser notifications for new critical alerts and an acknowledgement system for operators.

(Copy and paste the following into your AI IDE)

You are an expert front-end developer using React. In this final phase, we will add advanced interactive features to the DashboardPage component.

Phase 5 Requirements:

Browser Notifications for NEW Critical Faults:

In the DashboardPage component, create a useState to store a JavaScript Set of notified fault IDs, e.g., notifiedFaultIds.

When the API data is fetched, compare the incoming SC faults against the IDs in this set.

For any SC fault whose id is NOT in the set, it's a new fault.

Trigger a browser notification for each new critical fault. You will need to handle asking the user for permission first.

After triggering the notification, add the new fault's id to the notifiedFaultIds set to prevent duplicate alerts.

Fault Acknowledgement System using localStorage:

This feature will be implemented in the component that renders individual faults (like the LiveFaultFeed).

Add an "Acknowledge" Button: Next to each fault, add a button.

Save to localStorage: When the button is clicked, get the fault's unique id and add it to an array of acknowledged IDs stored in the browser's localStorage under a key like 'acknowledgedFaults'.

Check Status on Render: When rendering any fault on the screen, check if its id exists in the array from localStorage.

Apply Visual Style: If a fault has been acknowledged, apply a distinct visual style to it, such as changing its background color to gray or adding an "Acknowledged" badge. This style should override its normal status color.