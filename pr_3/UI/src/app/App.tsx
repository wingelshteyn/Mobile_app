import { useState } from "react";
import BackgroundFx from "./components/BackgroundFx";
import BottomNav from "./components/BottomNav";
import ChatsScreen from "./screens/ChatsScreen";
import NotesScreen from "./screens/NotesScreen";
import WeatherScreen from "./screens/WeatherScreen";
import MapScreen from "./screens/MapScreen";
import SettingsScreen from "./screens/SettingsScreen";
import { translations, Language } from "./i18n";
import { useLocalStorage } from "./hooks/useLocalStorage";

export default function App() {
  const [activeTab, setActiveTab] = useState("chats");
  const [language, setLanguage] = useLocalStorage<Language>("astroll_language", "ru");

  const t = (key: string) => {
    return translations[language][key as keyof typeof translations.ru] || key;
  };

  const renderScreen = () => {
    switch (activeTab) {
      case "chats":
        return <ChatsScreen t={t} />;
      case "notes":
        return <NotesScreen t={t} />;
      case "weather":
        return <WeatherScreen t={t} />;
      case "map":
        return <MapScreen t={t} />;
      case "settings":
        return <SettingsScreen t={t} language={language} onLanguageChange={setLanguage} />;
      default:
        return <ChatsScreen t={t} />;
    }
  };

  return (
    <div className="w-full h-screen overflow-hidden relative">
      <BackgroundFx />

      <div className="relative z-10 h-full flex flex-col">
        {renderScreen()}
      </div>

      <BottomNav
        activeTab={activeTab}
        onTabChange={setActiveTab}
        labels={{
          chats: t("chats"),
          notes: t("notes"),
          weather: t("weather"),
          map: t("map"),
          settings: t("settings")
        }}
      />
    </div>
  );
}