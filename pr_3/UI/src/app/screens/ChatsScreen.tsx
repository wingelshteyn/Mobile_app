import { useState } from "react";
import { Search, Plus, ArrowLeft, Send } from "lucide-react";
import GlassCard from "../components/GlassCard";
import GlassInput from "../components/GlassInput";
import GlassButton from "../components/GlassButton";
import Topbar from "../components/Topbar";
import { useLocalStorage } from "../hooks/useLocalStorage";

interface Message {
  id: string;
  text: string;
  sender: "me" | "other";
  timestamp: Date;
}

interface Chat {
  id: string;
  name: string;
  avatar: string;
  lastMessage: string;
  timestamp: string;
  online: boolean;
  unread: number;
}

interface ChatsScreenProps {
  t: (key: string) => string;
}

export default function ChatsScreen({ t }: ChatsScreenProps) {
  const [selectedChat, setSelectedChat] = useState<string | null>(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [messageInput, setMessageInput] = useState("");

  const [chats] = useLocalStorage<Chat[]>("astroll_chats", [
    {
      id: "1",
      name: "Анна Иванова",
      avatar: "👩",
      lastMessage: "Привет! Как дела?",
      timestamp: "14:30",
      online: true,
      unread: 2
    },
    {
      id: "2",
      name: "Дмитрий Смирнов",
      avatar: "👨",
      lastMessage: "Спасибо за помощь!",
      timestamp: "Вчера",
      online: false,
      unread: 0
    },
    {
      id: "3",
      name: "Мария Петрова",
      avatar: "👩‍🦰",
      lastMessage: "До встречи завтра",
      timestamp: "2 дня",
      online: true,
      unread: 1
    }
  ]);

  const [messages, setMessages] = useLocalStorage<Record<string, Message[]>>("astroll_messages", {
    "1": [
      { id: "1", text: "Привет! Как дела?", sender: "other", timestamp: new Date("2026-05-06T14:30:00") },
      { id: "2", text: "Привет! Всё отлично, спасибо!", sender: "me", timestamp: new Date("2026-05-06T14:31:00") },
      { id: "3", text: "Отлично! Что нового?", sender: "other", timestamp: new Date("2026-05-06T14:32:00") }
    ],
    "2": [
      { id: "1", text: "Помоги с проектом", sender: "other", timestamp: new Date("2026-05-05T10:00:00") },
      { id: "2", text: "Конечно, чем могу помочь?", sender: "me", timestamp: new Date("2026-05-05T10:05:00") },
      { id: "3", text: "Спасибо за помощь!", sender: "other", timestamp: new Date("2026-05-05T12:00:00") }
    ],
    "3": [
      { id: "1", text: "До встречи завтра", sender: "other", timestamp: new Date("2026-05-04T18:00:00") },
      { id: "2", text: "До встречи!", sender: "me", timestamp: new Date("2026-05-04T18:01:00") }
    ]
  });

  const filteredChats = chats.filter(chat =>
    chat.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const sendMessage = () => {
    if (!messageInput.trim() || !selectedChat) return;

    const newMessage: Message = {
      id: Date.now().toString(),
      text: messageInput,
      sender: "me",
      timestamp: new Date()
    };

    setMessages(prev => ({
      ...prev,
      [selectedChat]: [...(prev[selectedChat] || []), newMessage]
    }));
    setMessageInput("");
  };

  if (selectedChat) {
    const chat = chats.find(c => c.id === selectedChat);
    const chatMessages = messages[selectedChat] || [];

    return (
      <div className="h-full flex flex-col">
        <Topbar
          title={chat?.name || ""}
          leftAction={
            <button
              onClick={() => setSelectedChat(null)}
              className="text-[#d8dce6] hover:text-[#7ec8f0] transition-colors"
            >
              <ArrowLeft size={20} />
            </button>
          }
          rightAction={
            <div className="flex items-center gap-2">
              <span className="text-[32px]">{chat?.avatar}</span>
              {chat?.online && (
                <div className="w-2 h-2 rounded-full bg-[#7ec8f0] shadow-[0_0_8px_rgba(126,200,240,0.6)]" />
              )}
            </div>
          }
        />

        <div className="flex-1 overflow-y-auto px-4 py-4 space-y-3 pb-24">
          {chatMessages.map(msg => (
            <div
              key={msg.id}
              className={`flex ${msg.sender === "me" ? "justify-end" : "justify-start"}`}
            >
              <GlassCard
                className={`max-w-[75%] ${
                  msg.sender === "me"
                    ? "bg-[rgba(126,200,240,0.15)] border-[rgba(126,200,240,0.3)]"
                    : ""
                }`}
              >
                <p className="text-[#e8e9f0]">{msg.text}</p>
                <span className="text-[10px] text-[#9a9eac] mt-1 block">
                  {new Date(msg.timestamp).toLocaleTimeString("ru-RU", {
                    hour: "2-digit",
                    minute: "2-digit"
                  })}
                </span>
              </GlassCard>
            </div>
          ))}
        </div>

        <div className="fixed bottom-16 left-0 right-0 p-4 backdrop-blur-[14px]" style={{ background: 'rgba(18, 22, 30, 0.88)' }}>
          <div className="flex gap-2">
            <GlassInput
              placeholder={t("typeMessage")}
              value={messageInput}
              onChange={e => setMessageInput(e.target.value)}
              onKeyPress={e => e.key === "Enter" && sendMessage()}
              className="flex-1"
            />
            <GlassButton onClick={sendMessage} variant="accent">
              <Send size={18} />
            </GlassButton>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="h-full flex flex-col">
      <Topbar
        title={t("chatsTitle")}
        rightAction={
          <button className="text-[#d8dce6] hover:text-[#7ec8f0] transition-colors">
            <Plus size={20} />
          </button>
        }
      />

      <div className="p-4">
        <GlassInput
          placeholder={t("searchChats")}
          value={searchQuery}
          onChange={e => setSearchQuery(e.target.value)}
          icon={<Search size={18} />}
        />
      </div>

      <div className="flex-1 overflow-y-auto px-4 pb-24 space-y-3">
        {filteredChats.map(chat => (
          <GlassCard key={chat.id} onClick={() => setSelectedChat(chat.id)}>
            <div className="flex items-center gap-3">
              <div className="relative">
                <span className="text-[40px]">{chat.avatar}</span>
                {chat.online && (
                  <div className="absolute bottom-0 right-0 w-3 h-3 rounded-full bg-[#7ec8f0] border-2 border-[#08090d] shadow-[0_0_8px_rgba(126,200,240,0.6)]" />
                )}
              </div>

              <div className="flex-1 min-w-0">
                <div className="flex items-baseline justify-between mb-1">
                  <h3 className="text-[#d8dce6] font-[700] truncate">{chat.name}</h3>
                  <span className="text-[11px] text-[#9a9eac] ml-2">{chat.timestamp}</span>
                </div>
                <p className="text-[#9a9eac] text-sm truncate">{chat.lastMessage}</p>
              </div>

              {chat.unread > 0 && (
                <div className="w-6 h-6 rounded-full bg-[#7ec8f0] flex items-center justify-center">
                  <span className="text-[#08090d] text-xs font-[800]">{chat.unread}</span>
                </div>
              )}
            </div>
          </GlassCard>
        ))}
      </div>
    </div>
  );
}
