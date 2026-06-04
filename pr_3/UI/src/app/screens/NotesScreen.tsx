import { useState } from "react";
import { Plus, Trash2, Edit2, Save } from "lucide-react";
import GlassCard from "../components/GlassCard";
import GlassInput from "../components/GlassInput";
import GlassButton from "../components/GlassButton";
import Topbar from "../components/Topbar";
import { useLocalStorage } from "../hooks/useLocalStorage";

interface Note {
  id: string;
  title: string;
  content: string;
  timestamp: Date;
}

interface NotesScreenProps {
  t: (key: string) => string;
}

export default function NotesScreen({ t }: NotesScreenProps) {
  const [notes, setNotes] = useLocalStorage<Note[]>("astroll_notes", [
    {
      id: "1",
      title: "Список покупок",
      content: "Молоко, хлеб, яйца, кофе",
      timestamp: new Date("2026-05-06T10:00:00")
    },
    {
      id: "2",
      title: "Идеи для проекта",
      content: "1. Добавить темную тему\n2. Интеграция с API\n3. Улучшить производительность",
      timestamp: new Date("2026-05-05T15:30:00")
    }
  ]);

  const [editingNote, setEditingNote] = useState<string | null>(null);
  const [isCreating, setIsCreating] = useState(false);
  const [formTitle, setFormTitle] = useState("");
  const [formContent, setFormContent] = useState("");

  const startEdit = (note: Note) => {
    setEditingNote(note.id);
    setFormTitle(note.title);
    setFormContent(note.content);
    setIsCreating(false);
  };

  const startCreate = () => {
    setIsCreating(true);
    setEditingNote(null);
    setFormTitle("");
    setFormContent("");
  };

  const saveNote = () => {
    if (!formTitle.trim()) return;

    if (isCreating) {
      const newNote: Note = {
        id: Date.now().toString(),
        title: formTitle,
        content: formContent,
        timestamp: new Date()
      };
      setNotes(prev => [newNote, ...prev]);
    } else if (editingNote) {
      setNotes(prev =>
        prev.map(note =>
          note.id === editingNote
            ? { ...note, title: formTitle, content: formContent, timestamp: new Date() }
            : note
        )
      );
    }

    setIsCreating(false);
    setEditingNote(null);
    setFormTitle("");
    setFormContent("");
  };

  const deleteNote = (id: string) => {
    setNotes(prev => prev.filter(note => note.id !== id));
    if (editingNote === id) {
      setEditingNote(null);
      setIsCreating(false);
    }
  };

  const cancelEdit = () => {
    setIsCreating(false);
    setEditingNote(null);
    setFormTitle("");
    setFormContent("");
  };

  return (
    <div className="h-full flex flex-col">
      <Topbar
        title={t("notesTitle")}
        rightAction={
          <button
            onClick={startCreate}
            className="text-[#d8dce6] hover:text-[#7ec8f0] transition-colors"
          >
            <Plus size={20} />
          </button>
        }
      />

      <div className="flex-1 overflow-y-auto px-4 py-4 pb-24 space-y-3">
        {(isCreating || editingNote) && (
          <GlassCard className="space-y-3">
            <GlassInput
              placeholder={t("noteTitle")}
              value={formTitle}
              onChange={e => setFormTitle(e.target.value)}
            />
            <textarea
              placeholder={t("noteContent")}
              value={formContent}
              onChange={e => setFormContent(e.target.value)}
              rows={6}
              className="
                w-full rounded-[10px] px-3 py-2.5
                bg-[rgba(0,0,0,0.36)]
                border border-[rgba(255,255,255,0.10)]
                text-[#e8e9f0] placeholder:text-[#9a9eac]
                transition-all duration-150
                focus:outline-none focus:outline-2 focus:outline-dashed focus:outline-[rgba(180,190,210,0.55)]
                focus:bg-[rgba(255,255,255,0.07)]
                resize-none
              "
            />
            <div className="flex gap-2">
              <GlassButton onClick={saveNote} variant="accent" className="flex-1">
                <Save size={16} className="inline mr-2" />
                {t("save")}
              </GlassButton>
              {editingNote && (
                <GlassButton onClick={() => deleteNote(editingNote)} variant="danger">
                  <Trash2 size={16} />
                </GlassButton>
              )}
              {(isCreating || editingNote) && (
                <GlassButton onClick={cancelEdit}>
                  Отмена
                </GlassButton>
              )}
            </div>
          </GlassCard>
        )}

        {notes.map(note => (
          <GlassCard
            key={note.id}
            className={editingNote === note.id ? "opacity-50" : ""}
          >
            <div className="space-y-2">
              <div className="flex items-start justify-between gap-3">
                <h3 className="text-[#d8dce6] font-[800] tracking-wide uppercase text-xs">
                  {note.title}
                </h3>
                <button
                  onClick={() => startEdit(note)}
                  className="text-[#9a9eac] hover:text-[#7ec8f0] transition-colors shrink-0"
                  disabled={editingNote === note.id}
                >
                  <Edit2 size={16} />
                </button>
              </div>
              <p className="text-[#e8e9f0] text-sm whitespace-pre-wrap">{note.content}</p>
              <span className="text-[10px] text-[#9a9eac] block">
                {new Date(note.timestamp).toLocaleString("ru-RU", {
                  day: "2-digit",
                  month: "short",
                  hour: "2-digit",
                  minute: "2-digit"
                })}
              </span>
            </div>
          </GlassCard>
        ))}

        {notes.length === 0 && !isCreating && (
          <div className="text-center py-12">
            <p className="text-[#9a9eac]">Нет заметок. Создайте первую!</p>
          </div>
        )}
      </div>
    </div>
  );
}
