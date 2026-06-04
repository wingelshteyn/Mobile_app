import { MessageCircle, StickyNote, Cloud, Map, Settings } from "lucide-react";

interface BottomNavProps {
  activeTab: string;
  onTabChange: (tab: string) => void;
  labels: {
    chats: string;
    notes: string;
    weather: string;
    map: string;
    settings: string;
  };
}

export default function BottomNav({ activeTab, onTabChange, labels }: BottomNavProps) {
  const tabs = [
    { id: "chats", icon: MessageCircle, label: labels.chats },
    { id: "notes", icon: StickyNote, label: labels.notes },
    { id: "weather", icon: Cloud, label: labels.weather },
    { id: "map", icon: Map, label: labels.map },
    { id: "settings", icon: Settings, label: labels.settings }
  ];

  return (
    <div
      className="fixed bottom-0 left-0 right-0 z-40 backdrop-blur-[14px] border-t border-[rgba(255,255,255,0.10)]"
      style={{
        background: 'rgba(18, 22, 30, 0.88)'
      }}
    >
      <div className="flex items-center justify-around px-2 py-2">
        {tabs.map(({ id, icon: Icon, label }) => (
          <button
            key={id}
            onClick={() => onTabChange(id)}
            className={`
              flex flex-col items-center gap-1 px-3 py-2 rounded-lg
              transition-all duration-150
              ${activeTab === id
                ? 'text-[#7ec8f0] bg-[rgba(126,200,240,0.1)]'
                : 'text-[#9a9eac] hover:text-[#d8dce6] hover:bg-[rgba(255,255,255,0.05)]'
              }
            `}
          >
            <Icon size={20} strokeWidth={1.5} />
            <span className="text-[10px] font-[700] uppercase tracking-wider">
              {label}
            </span>
          </button>
        ))}
      </div>
    </div>
  );
}
