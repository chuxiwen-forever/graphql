import './App.css';
import {Component} from "react";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import AuthPage from "./pages/Auth";
import EventPage from "./pages/Event";
import BookingPage from "./pages/Booking";
import MainNavigation from "./components/Navigation/MainNavigation";

class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <MainNavigation/>
                <main className="main-content">
                    <Routes>
                        <Route path="/" element={<Navigate to="/auth"/>}></Route>
                        <Route path="/auth" element={<AuthPage/>}></Route>
                        <Route path="/events" element={<EventPage/>}></Route>
                        <Route path="/bookings" element={<BookingPage/>}></Route>
                    </Routes>
                </main>
            </BrowserRouter>
        )
    }
}

export default App;
