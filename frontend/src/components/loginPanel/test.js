import {
  Box,
  Button,
  Checkbox,
  Container,
  FormControlLabel,
  Grid,
  Link,
  Typography,
  TextField,
} from "@mui/material";
import React, { useState } from "react";

const [email, setEmail] = useState("");
const [password, setPassword] = useState("");

const handleSubmit = (event) => {
  event.preventDefault(); // Zatrzymanie domyślnego działania przeglądarki
  console.log("Email:", email);
  console.log("Password:", password);
};

function LoginPanel() {
  return (
    <>
      <Container component="main" maxWidth="xs">
        <Box sx={{ borderRadius: 8 }}>
          <Typography component="h1" variant="h5">
            Sign In
          </Typography>
          <Box component="form" sx={{ mt: 2 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              type="email"
              label="Email Address"
              name="email"
              autoFocus
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              sx={{ bgcolor: "#E8F5E9" }} // zielone tło
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              sx={{ bgcolor: "#E8F5E9" }} // zielone tło
            />
            <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              onClick={handleSubmit}
              sx={{ mt: 3, mb: 2, bgcolor: "#4CAF50", color: "#FFFFFF" }} // zielony przycisk
            >
              Sign In
            </Button>
            <Grid>
              <Link href="" color="#4CAF50">
                Forgot password?
              </Link>
            </Grid>
            <Grid className="footer" sx={{ color: "#4CAF50" }}>
              <Typography component="h5">
                Don't have an account?{" "}
                <Link href="" color="#4CAF50">
                  Sign Up
                </Link>
              </Typography>
            </Grid>
          </Box>
        </Box>
      </Container>
    </>
  );
}

export default LoginPanel;
