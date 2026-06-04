import { Cloud, CloudRain, CloudSnow, Sun, Wind, Droplets, Thermometer } from "lucide-react";
import GlassCard from "../components/GlassCard";
import Topbar from "../components/Topbar";

interface WeatherScreenProps {
  t: (key: string) => string;
}

// Mock weather data (в реальном приложении это будет API вызов)
const mockWeatherData = {
  current: {
    temp: 5,
    feelsLike: 2,
    condition: "Облачно",
    icon: "cloud",
    humidity: 75,
    windSpeed: 12
  },
  forecast: [
    { day: "Сегодня", temp: 5, icon: "cloud", condition: "Облачно" },
    { day: "Завтра", temp: 7, icon: "sun", condition: "Солнечно" },
    { day: "Ср", temp: 4, icon: "rain", condition: "Дождь" },
    { day: "Чт", temp: 2, icon: "snow", condition: "Снег" },
    { day: "Пт", temp: 6, icon: "cloud", condition: "Облачно" }
  ]
};

const weatherIcons = {
  cloud: Cloud,
  sun: Sun,
  rain: CloudRain,
  snow: CloudSnow
};

export default function WeatherScreen({ t }: WeatherScreenProps) {
  const CurrentIcon = weatherIcons[mockWeatherData.current.icon as keyof typeof weatherIcons];

  return (
    <div className="h-full flex flex-col">
      <Topbar title={t("weatherTitle")} />

      <div className="flex-1 overflow-y-auto px-4 py-4 pb-24 space-y-4">
        {/* Current Weather */}
        <GlassCard className="text-center">
          <p className="text-[#9a9eac] text-xs uppercase tracking-wider mb-3">
            {t("currentWeather")}
          </p>
          <div className="flex items-center justify-center mb-4">
            <CurrentIcon size={80} className="text-[#7ec8f0]" strokeWidth={1.5} />
          </div>
          <div className="text-[64px] font-[800] text-[#d8dce6] leading-none mb-2">
            {mockWeatherData.current.temp}°
          </div>
          <p className="text-[#e8e9f0] mb-4">{mockWeatherData.current.condition}</p>

          <div className="grid grid-cols-3 gap-3 mt-4">
            <div className="space-y-2">
              <Thermometer size={24} className="text-[#7ec8f0] mx-auto" strokeWidth={1.5} />
              <p className="text-xs text-[#9a9eac]">{t("feelsLike")}</p>
              <p className="text-[#d8dce6] font-[700]">{mockWeatherData.current.feelsLike}°</p>
            </div>
            <div className="space-y-2">
              <Droplets size={24} className="text-[#7ec8f0] mx-auto" strokeWidth={1.5} />
              <p className="text-xs text-[#9a9eac]">{t("humidity")}</p>
              <p className="text-[#d8dce6] font-[700]">{mockWeatherData.current.humidity}%</p>
            </div>
            <div className="space-y-2">
              <Wind size={24} className="text-[#7ec8f0] mx-auto" strokeWidth={1.5} />
              <p className="text-xs text-[#9a9eac]">{t("wind")}</p>
              <p className="text-[#d8dce6] font-[700]">{mockWeatherData.current.windSpeed} км/ч</p>
            </div>
          </div>
        </GlassCard>

        {/* Forecast */}
        <div>
          <h3 className="text-[#d8dce6] font-[800] tracking-wider uppercase text-xs mb-3 px-1">
            {t("forecast")}
          </h3>
          <div className="space-y-2">
            {mockWeatherData.forecast.map((day, index) => {
              const Icon = weatherIcons[day.icon as keyof typeof weatherIcons];
              return (
                <GlassCard key={index}>
                  <div className="flex items-center justify-between">
                    <span className="text-[#e8e9f0] font-[700] w-20">{day.day}</span>
                    <div className="flex items-center gap-3 flex-1 justify-center">
                      <Icon size={28} className="text-[#7ec8f0]" strokeWidth={1.5} />
                      <span className="text-[#9a9eac] text-sm">{day.condition}</span>
                    </div>
                    <span className="text-[#d8dce6] font-[800] text-xl w-16 text-right">
                      {day.temp}°
                    </span>
                  </div>
                </GlassCard>
              );
            })}
          </div>
        </div>

        {/* API Note */}
        <GlassCard className="border-[rgba(126,200,240,0.3)]">
          <p className="text-[10px] text-[#9a9eac] text-center">
            📡 Данные погоды (Mock API)
            <br />
            <span className="text-[#7ec8f0]">
              В реальном приложении: OpenWeatherMap API
            </span>
          </p>
        </GlassCard>
      </div>
    </div>
  );
}
