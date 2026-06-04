import { MapPin, Navigation } from "lucide-react";
import GlassCard from "../components/GlassCard";
import Topbar from "../components/Topbar";

interface MapScreenProps {
  t: (key: string) => string;
}

export default function MapScreen({ t }: MapScreenProps) {
  return (
    <div className="h-full flex flex-col">
      <Topbar title={t("mapTitle")} />

      <div className="flex-1 overflow-y-auto px-4 py-4 pb-24 space-y-4">
        {/* Location Card */}
        <GlassCard>
          <div className="flex items-center gap-3 mb-3">
            <MapPin size={24} className="text-[#7ec8f0]" />
            <div>
              <h3 className="text-[#d8dce6] font-[800] tracking-wider uppercase text-xs">
                {t("yourLocation")}
              </h3>
              <p className="text-[#9a9eac] text-sm">Москва, Россия</p>
            </div>
          </div>
          <div className="flex items-center gap-2 text-xs text-[#9a9eac]">
            <Navigation size={14} />
            <span>55.7558° N, 37.6173° E</span>
          </div>
        </GlassCard>

        {/* Map Container */}
        <GlassCard className="aspect-[4/3] relative overflow-hidden">
          {/* OpenStreetMap embedded iframe */}
          <iframe
            title="OpenStreetMap"
            width="100%"
            height="100%"
            frameBorder="0"
            scrolling="no"
            src="https://www.openstreetmap.org/export/embed.html?bbox=37.5173%2C55.6558%2C37.7173%2C55.8558&layer=mapnik&marker=55.7558%2C37.6173"
            className="rounded-lg"
            style={{
              border: '1px solid rgba(255,255,255,0.1)'
            }}
          />

          {/* Overlay with glass effect for visual consistency */}
          <div className="absolute bottom-3 left-3 right-3">
            <GlassCard className="backdrop-blur-[8px]">
              <div className="flex items-center justify-between">
                <div>
                  <p className="text-[#d8dce6] font-[700] text-sm">Москва</p>
                  <p className="text-[#9a9eac] text-xs">Центр города</p>
                </div>
                <MapPin size={20} className="text-[#7ec8f0]" />
              </div>
            </GlassCard>
          </div>
        </GlassCard>

        {/* Map Info */}
        <GlassCard className="border-[rgba(126,200,240,0.3)]">
          <p className="text-[10px] text-[#9a9eac] text-center">
            🗺️ Интерактивная карта OpenStreetMap
            <br />
            <span className="text-[#7ec8f0]">
              Показывает текущее местоположение пользователя
            </span>
          </p>
        </GlassCard>

        {/* Nearby Places */}
        <div>
          <h3 className="text-[#d8dce6] font-[800] tracking-wider uppercase text-xs mb-3 px-1">
            Рядом с вами
          </h3>
          <div className="space-y-2">
            {[
              { name: "Кафе 'Звёздная пыль'", distance: "250 м", type: "Кафе" },
              { name: "Парк Горького", distance: "1.2 км", type: "Парк" },
              { name: "Библиотека им. Ленина", distance: "800 м", type: "Библиотека" }
            ].map((place, index) => (
              <GlassCard key={index}>
                <div className="flex items-center justify-between">
                  <div>
                    <p className="text-[#e8e9f0] font-[700]">{place.name}</p>
                    <p className="text-[#9a9eac] text-xs">{place.type}</p>
                  </div>
                  <span className="text-[#7ec8f0] text-sm font-[700]">{place.distance}</span>
                </div>
              </GlassCard>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
