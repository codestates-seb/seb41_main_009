import styled from 'styled-components';
import useSidebarStore from '../../../store/sidebarStore';
import Selected from '../../../styles/Selected';
import { SidebarTagsButton } from '../../atoms/Buttons';

const SidebarTagsContainer = ({ isClicked, tags, onClick }) => {
  const { currentTab } = useSidebarStore(state => state);

  return (
    <Container isClicked={isClicked} tags={tags}>
      {tags.map(tag => {
        const [enName, krName] = tag;
        return currentTab === krName ? (
          <SelectedButton to={`/posts/${enName}`} key={enName} onClick={onClick}>
            {krName}
          </SelectedButton>
        ) : (
          <SidebarTagsButton to={`/posts/${enName}`} key={enName} onClick={onClick}>
            {krName}
          </SidebarTagsButton>
        );
      })}
    </Container>
  );
};

const Container = styled.div`
  height: ${props => (props.isClicked ? `${props.tags.length * 30}px` : '0px')};
  overflow: hidden;
  transition: height 200ms ease-out;
`;

const SelectedButton = styled(SidebarTagsButton)`
  ${Selected}
`;

export default SidebarTagsContainer;
