import React, { useState, useEffect } from "react";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import "./App.css";

const urgencyOptions = ["STANDARD", "EXPRESS", "SAME_DAY"];
const cargoSizeOptions = ["SMALL", "LARGE"];

const getWorkloadPercentage = (workload) => {
  const multipliers = {
    NORMAL: 1.0,
    ELEVATED: 1.2,
    HIGH: 1.4,
    VERY_HIGH: 1.6,
  };
  const multiplier = multipliers[workload] || 1.0;
  return Math.round((multiplier - 1) * 100);
};

function App() {
  const [form, setForm] = useState({
    distance: 50,
    isFragile: false,
    cargoSize: "SMALL",
    workload: "NORMAL",
    urgency: "STANDARD",
  });

  const [result, setResult] = useState({ cost: null, error: null });
  const [workloadInfo, setWorkloadInfo] = useState("");

  useEffect(() => {
    const workloadOptions = ["NORMAL", "ELEVATED", "HIGH", "VERY_HIGH"];
    const updateWorkload = () => {
      const randomIndex = Math.floor(Math.random() * workloadOptions.length);
      const randomWorkload = workloadOptions[randomIndex];
      setForm(prev => ({ ...prev, workload: randomWorkload }));
    };
    updateWorkload();
    const intervalId = setInterval(updateWorkload, 120000);
    return () => clearInterval(intervalId);
  }, []);

  useEffect(() => {
    const percent = getWorkloadPercentage(form.workload);
    setWorkloadInfo(`Текущая загрузка сервиса: ${form.workload} (увеличение стоимости на ${percent}%)`);
  }, [form.workload]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    if (name === "workload") return;
    setForm({
      ...form,
      [name]: type === "checkbox" ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/delivery/calculate", form);
      setResult({ cost: response.data, error: null });
    } catch (error) {
      if (error.response && error.response.data && error.response.data.message) {
        setResult({ cost: null, error: error.response.data.message });
      } else {
        setResult({ cost: null, error: "Unknown error" });
      }
    }
  };

  return (
      <div className="container my-4">
        <h1 className="app-title">Сервис доставки</h1>
        <div className="card p-4">
          <h2 className="card-title text-center mb-4">Калькулятор доставки</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="distance">Расстояние (км):</label>
              <input
                  type="number"
                  id="distance"
                  name="distance"
                  value={form.distance}
                  onChange={handleChange}
                  min="0"
                  max="100"
                  step="any"
                  required
                  className="form-control"
              />
            </div>

            <div className="form-group">
              <label>Размер груза:</label>
              <div className="toggle-group">
                {cargoSizeOptions.map((size) => (
                    <label key={size} className={`toggle-label ${form.cargoSize === size ? "active" : ""}`}>
                      <input
                          type="radio"
                          name="cargoSize"
                          value={size}
                          checked={form.cargoSize === size}
                          onChange={handleChange}
                      />
                      <span>{size === "SMALL" ? "Маленький" : "Большой"}</span>
                    </label>
                ))}
              </div>
            </div>

            <div className="form-group">
              <label>Срочность доставки:</label>
              <div className="toggle-group">
                {urgencyOptions.map((urgency) => (
                    <label key={urgency} className={`toggle-label ${form.urgency === urgency ? "active" : ""}`}>
                      <input
                          type="radio"
                          name="urgency"
                          value={urgency}
                          checked={form.urgency === urgency}
                          onChange={handleChange}
                      />
                      <span>
                    {urgency === "STANDARD"
                        ? "Стандартная"
                        : urgency === "EXPRESS"
                            ? "Экспресс"
                            : "Same Day"}
                  </span>
                    </label>
                ))}
              </div>
            </div>

            <div className="form-group">
              <label>Хрупкий груз:</label>
              <div className="toggle-group">
                <label className="toggle-label">
                  <div className="switch">
                    <input
                        type="checkbox"
                        name="isFragile"
                        checked={form.isFragile}
                        onChange={handleChange}
                    />
                    <span className="slider"></span>
                  </div>
                  <span>Да</span>
                </label>
              </div>
            </div>

            <div className="d-flex align-items-center">
              <button type="submit" className="submit-button">Рассчитать стоимость</button>
              <div className="workload-info">{workloadInfo}</div>
            </div>
          </form>
        </div>
        <div className="result text-center mt-4">
          {result.cost !== null && (
              <h3>Стоимость доставки: {result.cost} RUB</h3>
          )}
          {result.error && (
              <h3 className="error">Ошибка: {result.error}</h3>
          )}
        </div>
      </div>
  );
}

export default App;
