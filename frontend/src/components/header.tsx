"use client";

import { Link } from "react-router-dom";
import { StickyHeader } from "./sticky-header";
import { HamburgerTwo } from "./hamburger-two";
import { UserMenu } from "./user-menu.tsx";

import React, { MouseEvent, useEffect, useState } from "react";

type HeaderProps = {
  isLoggedIn: boolean;
};

export const Header: React.FC<HeaderProps> = ({ isLoggedIn }) => {
  const menuItems: { name: string; href: string }[] = [
    { name: "Home", href: "/" },
    { name: "Books", href: "/books" },
  ];

  const [isMenuClicked, setIsMenuClicked] = useState<boolean>(false);

  const toggleMenu = (): void => {
    // Store previous scroll
    const scrollY = document.body.style.top;

    // Set current scroll
    document.body.style.top = isMenuClicked ? "" : `-${window.scrollY}px`;

    // prevent scroll
    document.body.style.position = isMenuClicked ? "" : "fixed";
    document.body.style.overflowX = isMenuClicked ? "" : "hidden";

    // scroll back to original position
    isMenuClicked && window.scrollTo(0, parseInt(scrollY || "0") * -1);

    setIsMenuClicked(!isMenuClicked);
  };

  type ButtonHoverState = {
    left: number;
    height: number;
    width: number;
    duration: number;
    opacity: 1 | 0;
  };
  const [menuButtonHoverState, setMenuButtonHoverState] = useState<ButtonHoverState>({
    left: 0,
    height: 0,
    width: 0,
    duration: 0,
    opacity: 0,
  });

  const onButtonMouseEnter = (event: MouseEvent<HTMLButtonElement>): void => {
    const target: HTMLButtonElement = event.target as HTMLButtonElement;

    setMenuButtonHoverState({
      left: target.offsetLeft,
      height: target.offsetHeight,
      width: target.offsetWidth,
      duration: 150 * menuButtonHoverState.opacity,
      opacity: 1,
    });
  };

  const onMenuMouseLeave = (): void => {
    setMenuButtonHoverState({
      left: 0,
      height: 0,
      width: 0,
      duration: 0,
      opacity: 0,
    });
  };

  useEffect(() => {
    function handleResize() {
      if (window.innerWidth > 1024) {
        isMenuClicked && toggleMenu();
      }
    }

    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, [isMenuClicked]);

  return (
    <>
      <StickyHeader isFixed={isMenuClicked}>
        <div className="flex h-1/2 w-full items-center justify-between px-8">
          <div className="flex h-full">
            <Link to={"/"} className="flex h-full items-center">
              <img src="/books_icon.png" alt="logo" className="mr-2 h-full" />
              <span className="mr-10 text-xl font-extrabold">Nerdify</span>
            </Link>
            <div className="hidden items-center text-sm text-gray-600 lg:flex" onMouseLeave={onMenuMouseLeave}>
              <div
                className="absolute rounded-full bg-border transition-all duration-300"
                style={{
                  left: menuButtonHoverState.left + "px",
                  width: menuButtonHoverState.width + "px",
                  height: menuButtonHoverState.height + "px",
                  opacity: menuButtonHoverState.opacity,
                  transitionProperty: "width, left",
                  transitionTimingFunction: "cubic-bezier(0.4, 0, 0.2, 1)",
                  transitionDuration: menuButtonHoverState.duration + "ms",
                }}
              />
              {menuItems.map((item, index) => (
                <Link key={item.name + index} to={item.href}>
                  <button
                    className="relative cursor-pointer rounded-full border-none px-3 py-2 font-light text-gray-600 hover:text-gray-900"
                    onMouseEnter={onButtonMouseEnter}
                  >
                    {item.name}
                  </button>
                </Link>
              ))}
            </div>
          </div>
          <UserMenu className="hidden lg:flex" isLoggedIn={isLoggedIn} />
          <div className="flex justify-self-end lg:hidden">
            <HamburgerTwo onHamburgerClick={toggleMenu} isActive={isMenuClicked} />
          </div>
        </div>
      </StickyHeader>
      <div className={`nav-mobile ${isMenuClicked ? "" : "hidden"}`}>
        <UserMenu className="flex flex-col" isLoggedIn={isLoggedIn} />
        <ul>
          {menuItems.map((item, index) => (
            <Link key={item.name + index * 123} to={item.href} onClick={() => setIsMenuClicked(!isMenuClicked)}>
              <li className="item">{item.name}</li>
            </Link>
          ))}
        </ul>
      </div>
    </>
  );
};
