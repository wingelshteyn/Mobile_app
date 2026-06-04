import { Globe, Moon, Info } from "lucide-react";
import GlassCard from "../components/GlassCard";
import Topbar from "../components/Topbar";

interface SettingsScreenProps {
  t: (key: string) => string;
  language: "ru" | "en";
  onLanguageChange: (lang: "ru" | "en") => void;
}

export default function SettingsScreen({ t, language, onLanguageChange }: SettingsScreenProps) {
  return (
    <div className="h-full flex flex-col">
      <Topbar title={t("settingsTitle")} />

      <div className="flex-1 overflow-y-auto px-4 py-4 pb-24 space-y-4">
        {/* Language Settings */}
        <div>
          <h3 className="text-[#d8dce6] font-[800] tracking-wider uppercase text-xs mb-3 px-1 flex items-center gap-2">
            <Globe size={14} />
            {t("language")}
          </h3>
          <GlassCard>
            <div className="space-y-2">
              <button
                onClick={() => onLanguageChange("ru")}
                className={`
                  w-full px-4 py-3 rounded-lg text-left transition-all
                  ${language === "ru"
                    ? "bg-[rgba(126,200,240,0.2)] border border-[rgba(126,200,240,0.4)] text-[#7ec8f0]"
                    : "border border-transparent text-[#e8e9f0] hover:bg-[rgba(255,255,255,0.05)]"
                  }
                `}
              >
                <p className="font-[700]">🇷🇺 {t("russian")}</p>
              </button>
              <button
                onClick={() => onLanguageChange("en")}
                className={`
                  w-full px-4 py-3 rounded-lg text-left transition-all
                  ${language === "en"
                    ? "bg-[rgba(126,200,240,0.2)] border border-[rgba(126,200,240,0.4)] text-[#7ec8f0]"
                    : "border border-transparent text-[#e8e9f0] hover:bg-[rgba(255,255,255,0.05)]"
                  }
                `}
              >
                <p className="font-[700]">🇬🇧 {t("english")}</p>
              </button>
            </div>
          </GlassCard>
        </div>

        {/* Theme Settings */}
        <div>
          <h3 className="text-[#d8dce6] font-[800] tracking-wider uppercase text-xs mb-3 px-1 flex items-center gap-2">
            <Moon size={14} />
            {t("theme")}
          </h3>
          <GlassCard>
            <div className="flex items-center justify-between">
              <span className="text-[#e8e9f0]">{t("darkTheme")}</span>
              <div className="w-12 h-6 rounded-full bg-[rgba(126,200,240,0.3)] border border-[rgba(126,200,240,0.5)] relative">
                <div className="absolute right-1 top-1 w-4 h-4 rounded-full bg-[#7ec8f0] shadow-[0_0_8px_rgba(126,200,240,0.6)]" />
              </div>
            </div>
            <p className="text-[#9a9eac] text-xs mt-2">
              Тёмная тема AstRoll всегда активна
            </p>
          </GlassCard>
        </div>

        {/* About */}
        <div>
          <h3 className="text-[#d8dce6] font-[800] tracking-wider uppercase text-xs mb-3 px-1 flex items-center gap-2">
            <Info size={14} />
            {t("about")}
          </h3>
          <GlassCard>
            <div className="space-y-3">
              <div className="text-center">
                <h2 className="text-[#d8dce6] font-[800] text-2xl mb-1">AstRoll</h2>
                <p className="text-[#9a9eac] text-sm">Мистический мессенджер</p>
              </div>

              <div className="border-t border-[rgba(255,255,255,0.1)] pt-3 space-y-2">
                <div className="flex justify-between">
                  <span className="text-[#9a9eac] text-sm">{t("version")}</span>
                  <span className="text-[#e8e9f0] text-sm font-[700]">1.0.0</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-[#9a9eac] text-sm">Дата сборки</span>
                  <span className="text-[#e8e9f0] text-sm">06.05.2026</span>
                </div>
              </div>

              <div className="border-t border-[rgba(255,255,255,0.1)] pt-3">
                <p className="text-[10px] text-[#9a9eac] text-center leading-relaxed">
                  Создано с использованием glassmorphism дизайна,
                  React, Tailwind CSS и localStorage для хранения данных.
                  <br />
                  <span className="text-[#7ec8f0]">
                    Интеграции: OpenStreetMap, Weather API
                  </span>
                </p>
              </div>
            </div>
          </GlassCard>
        </div>

        {/* Features List */}
        <GlassCard className="border-[rgba(126,200,240,0.3)]">
          <h4 className="text-[#d8dce6] font-[800] tracking-wider uppercase text-xs mb-3">
            Возможности приложения
          </h4>
          <ul className="space-y-2 text-sm text-[#e8e9f0]">
            <li className="flex items-start gap-2">
              <span className="text-[#7ec8f0]">✓</span>
              <span>Чаты с сохранением в localStorage (БД функционал)</span>
            </li>
            <li className="flex items-start gap-2">
              <span className="text-[#7ec8f0]">✓</span>
              <span>Заметки с полным CRUD</span>
            </li>
            <li className="flex items-start gap-2">
              <span className="text-[#7ec8f0]">✓</span>
              <span>Интеграция с Weather API (mock)</span>
            </li>
            <li className="flex items-start gap-2">
              <span className="text-[#7ec8f0]">✓</span>
              <span>Интерактивная карта OpenStreetMap</span>
            </li>
            <li className="flex items-start gap-2">
              <span className="text-[#7ec8f0]">✓</span>
              <span>Многоязычность (RU/EN)</span>
            </li>
            <li className="flex items-start gap-2">
              <span className="text-[#7ec8f0]">✓</span>
              <span>Mobile-first responsive дизайн</span>
            </li>
          </ul>
        </GlassCard>
      </div>
    </div>
  );
}
